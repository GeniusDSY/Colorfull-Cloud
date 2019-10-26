package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 18:09
 * @desc : 订单数据库接口
 */
@Repository
public interface OrderDao {

    /**
     * 查询某用户的订单
     * @param userId 用户Id
     * @return 该用户的所有订单
     */
    List<OrderEntity> selectAllOrdersByUserId(Integer userId);

    /**
     * 插入新订单
     * @param orderEntity 新订单信息
     * @return true 成功 false 失败
     */
    Boolean insertOrder(OrderEntity orderEntity);

    /**
     * 删除订单
     * @param orderIdList 需要删除订单id列表
     * @return true 成功 false 失败
     */
    Boolean deleteOrdersByOrderIdList(List<String> orderIdList);

    /**
     * 修改订单状态
     * @param orderId 订单编号
     * @param status 订单状态（0：未支付；1：支付成功；2：活动优惠中；3：已取消）
     * @return
     */
    Boolean modifyOrderStatusByOrderId(@Param("orderId")String orderId,@Param("status")Integer status);

    /**
     * 成功支付
     * @param orderId 订单编号
     * @param payId 支付订单id
     * @return
     */
    Boolean modifyOrderSuccessByOrderId(@Param("orderId")String orderId,@Param("payId")String payId,@Param("payTime")String payTime);

    /**
     * 查询订单id是否存在
     * @param orderId 订单id
     * @return
     */
    OrderEntity selectOrderId(String orderId);

    /**
     * 查询已选时长
     * @param orderId
     * @return
     */
    Integer selectCycleTime(String orderId);

}
