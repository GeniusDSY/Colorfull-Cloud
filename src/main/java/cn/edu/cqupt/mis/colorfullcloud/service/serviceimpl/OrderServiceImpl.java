package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.dao.CourseDao;
import cn.edu.cqupt.mis.colorfullcloud.dao.InstitutionDao;
import cn.edu.cqupt.mis.colorfullcloud.dao.OrderDao;
import cn.edu.cqupt.mis.colorfullcloud.dao.ProductDao;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.OrderDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.ProductDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.CourseEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.OrderEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ProductEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.*;
import cn.edu.cqupt.mis.colorfullcloud.service.OrderService;
import cn.edu.cqupt.mis.colorfullcloud.util.ServiceUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.TransformUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    @Override
    public List<OrderVo> createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = (OrderEntity) TransformUtil.transformOne(orderDto,new OrderEntity());
        String orderId = UUIDUtil.getRandomString();
        orderEntity.setOrderId(orderId);
        List<ProductEntity> productEntityList = TransformUtil.transformList(orderDto.getProductDtoList(),new ArrayList<>(),ProductEntity.class);
        productEntityList.forEach(productEntity1 -> productEntity1.setOrderId(orderId));
        ServiceUtil.checkSqlExecuted(orderDao.insertOrder(orderEntity),productDao.insertProducts(productEntityList));
        return getAllUserOrders(orderDto.getUserId());
    }

    /**
     * 删除所选订单
     * @param userId 用户id
     * @param orderIdList 所选订单id数组
     * @return 删除后的所有
     */
    @Override
    public List<OrderVo> deleteOrders(Integer userId, List<String> orderIdList) {
        ServiceUtil.checkSqlExecuted(orderDao.deleteOrdersByOrderIdList(orderIdList));
        return getAllUserOrders(userId);
    }

    /**
     * 取消订单
     * @param userId
     * @param orderId
     * @return 取消后的所有订单（已取消的也展示）
     */
    @Override
    public List<OrderVo> cancelOrder(Integer userId,String orderId) {
        ServiceUtil.checkSqlExecuted(orderDao.modifyOrderStatusByOrderId(orderId,Status.CANCEL));
        return getAllUserOrders(userId);
    }

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
                    List<CourseEntity> courseEntity = courseDao.selectOrderCoursesByIds(productDao.selectCourseIdsByOrderIdAndInstitutionId(orderVo.getOrderId(), institutionVo.getInstitutionId()));
                    List<OrderCourseVo> orderCourseVoList = TransformUtil.transformList(courseEntity, new ArrayList<>(), OrderCourseVo.class);
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

}
