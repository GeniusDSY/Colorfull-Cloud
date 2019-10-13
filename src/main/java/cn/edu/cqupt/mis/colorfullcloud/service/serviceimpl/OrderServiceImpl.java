package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.dao.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.OrderDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.*;
import cn.edu.cqupt.mis.colorfullcloud.service.OrderService;
import cn.edu.cqupt.mis.colorfullcloud.util.ServiceUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.TransformUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 16:50
 * @desc :
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private ProductDao productDao;
    @Resource
    private InstitutionDao institutionDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private ActivityChildrenDao activityChildrenDao;
    @Resource
    private ActivityDao activityDao;
    @Resource
    private UUIDUtil uuidUtil;
    /**
     * 获取当前用户的所有订单
     * @return 返回该用户的订单集合
     */
    @Override
    public List<OrderVo> allUserOrders(Integer userId) {
        return getAllUserOrders(userId);
    }

    /**
     * 生成新订单
     * @param orderDto
     * @return 返回该用户的所有订单
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<OrderVo> createOrder(String childrenCard,OrderDto orderDto) {
        try {
            OrderEntity orderEntity = (OrderEntity) TransformUtil.transformOne(orderDto,new OrderEntity());
            String orderId = uuidUtil.getRandomOrderId();
            orderEntity.setOrderId(orderId);
            Integer activityId = orderDto.getActivityId();
            if(judgeActivityChildren(activityId,childrenCard,orderId)){
                List<ProductEntity> productEntityList = TransformUtil.transformList(orderDto.getProductDtoList(),new ArrayList<>(),ProductEntity.class);
                productEntityList.forEach(productEntity1 -> productEntity1.setOrderId(orderId));
                ServiceUtil.checkSqlExecuted(orderDao.insertOrder(orderEntity),productDao.insertProducts(productEntityList));
                return getAllUserOrders(orderDto.getUserId());
            }else {
                throw new ServerException("您已达到购买上限!!");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("OrderServiceImpl->createOrder()->" + e);
            throw new ServerException("您已达到购买上限!!");
        }
    }

    /**
     * 删除所选订单
     * @param userId 用户id
     * @param orderIdList 所选订单id数组
     * @return 删除后的所有
     */
    @Override
    public List<OrderVo> deleteOrders(Integer userId, List<String> orderIdList) {
        try {
            ServiceUtil.checkSqlExecuted(orderDao.deleteOrdersByOrderIdList(orderIdList));
            return getAllUserOrders(userId);
        }catch (Exception e){
            log.error("OrderServiceImpl->deleteOrders()->" + e);
            throw new ServerException("删除订单出现异常");
        }

    }

    /**
     * 取消订单
     * @param userId
     * @param orderId
     * @return 取消后的所有订单（已取消的也展示）
     */
    @Override
    public List<OrderVo> cancelOrder(Integer userId,String orderId) {
        try {
            ServiceUtil.checkSqlExecuted(orderDao.modifyOrderStatusByOrderId(orderId,Status.CANCEL));
            return getAllUserOrders(userId);
        }catch (Exception e){
            log.error("OrderServiceImpl->cancelOrder()->" + e);
            throw new ServerException("取消订单出现异常");
        }

    }

    /**
     * 查询活动剩余课时
     * @param childrenCard 购买孩子的身份证号
     * @param activityId   活动id
     * @return
     */
    @Override
    public Integer inquiryRemainTime(String childrenCard, Integer activityId) {
        //查询所有订单信息
        List<ActivityChildrenEntity> activityChildrenEntityList = activityChildrenDao.selectActivityChildrenByActivityIdAndChildrenCard(activityId,childrenCard);
        if (activityChildrenEntityList.size() == 0){
            ActivityEntity activityEntity = activityDao.selectActivityById(activityId);
            return activityEntity.getCount();
        }
        return inquiryRemainTime(activityChildrenEntityList);
    }

    /**
     * 查询剩余课时（已确定买过活动）
     * @param activityChildrenEntityList
     * @return
     */
    private Integer inquiryRemainTime(List<ActivityChildrenEntity> activityChildrenEntityList) {
        Integer usedTime = 0;
        //计算已选课时
        for (ActivityChildrenEntity entity : activityChildrenEntityList) {
            usedTime += orderDao.selectCycleTime(entity.getOrderId());
        }
        Integer activityId = activityChildrenEntityList.get(0).getActivityId();
        //获得剩余课时
        return activityDao.selectActivityById(activityId).getCount() - usedTime;
    }

    /**
     * 查询某用户的所有订单
     * @param userId
     * @return
     */
    private List<OrderVo> getAllUserOrders(Integer userId) {
        try {
            //获取当前用户下的所有订单
            //订单信息转换成VO
            List<OrderVo> orderVoList = TransformUtil.transformList(orderDao.selectAllOrdersByUserId(userId), new ArrayList<>(), OrderVo.class);
            //遍历获取订单详情
            for (OrderVo orderVo : orderVoList) {
                //获取某一订单下的所有机构id
                List<Integer> institutionIds = productDao.selectInstitutionIdsByOrderId(orderVo.getOrderId());
                //根据机构id集合查询各机构的具体信息并转换成VO
                List<OrderInstitutionVo> orderInstitutionVoList = TransformUtil.transformList(institutionDao.selectInstitutionsByIdList(institutionIds), new ArrayList<>(), OrderInstitutionVo.class);
                for (OrderInstitutionVo institutionVo : orderInstitutionVoList) {
                    //1、查询订单中所含机构的所有id//2、查询所有课程详情并转换成Vo//3、赋值给所属机构
                    List<CourseEntity> courseEntityList = courseDao.selectOrderCoursesByIds(productDao.selectCourseIdsByOrderIdAndInstitutionId(orderVo.getOrderId(), institutionVo.getInstitutionId()));
                    courseEntityList.forEach(
                            courseEntity ->
                                    courseEntity.setTeacherIntroduction(teacherDao.selectTeacherById(courseEntity.getTeacherId())));
                    List<OrderCourseVo> orderCourseVoList = TransformUtil.transformList(courseEntityList, new ArrayList<>(), OrderCourseVo.class);
                    for (OrderCourseVo orderCourseVo : orderCourseVoList) {
                        orderCourseVo.setCount(productDao.selectCountByOrderIdAndCourseId(orderVo.getOrderId(), orderCourseVo.getCourseId()));
                    }
                    institutionVo.setOrderCourseVoList(orderCourseVoList);
                }
                orderVo.setOrderProductList(orderInstitutionVoList);
            }
            return orderVoList;
        } catch (Exception e) {
            log.error("OrderServiceImpl->getAllUserOrders()->{}",e);
            throw new ServerException("获取订单信息出现异常！");
        }
    }

    private Boolean judgeActivityChildren(Integer activityId,String childrenCard,String orderId){
        if (activityId != 0){
            //查询目前已经生成的活动订单
            List<ActivityChildrenEntity> activityChildrenEntityList = activityChildrenDao.selectActivityChildrenByActivityIdAndChildrenCard(activityId,childrenCard);
            //已经有了活动订单
            if (activityChildrenEntityList != null && activityChildrenEntityList.size() != 0) {
                return inquiryRemainTime(activityChildrenEntityList) >= 0;
            }
            //没有活动订单
            else {
                ActivityChildrenEntity activityChildrenEntity = new ActivityChildrenEntity();
                activityChildrenEntity.setActivityId(activityId);
                activityChildrenEntity.setChildrenCard(childrenCard);
                activityChildrenEntity.setOrderId(orderId);
                ServiceUtil.checkSqlExecuted(activityChildrenDao.insertActivityChildren(activityChildrenEntity));
            }
        }
        return true;
    }

}
