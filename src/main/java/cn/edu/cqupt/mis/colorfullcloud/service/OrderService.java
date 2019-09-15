package cn.edu.cqupt.mis.colorfullcloud.service;

import cn.edu.cqupt.mis.colorfullcloud.domain.dto.OrderDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.OrderVo;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 16:48
 * @desc :
 */
public interface OrderService {

    /**
     * 获取当前用户的所有订单
     * @param userId 用户id
     * @return 返回该用户的订单集合
     */
    List<OrderVo> allUserOrders(Integer userId);

    /**
     * 生成新订单
     * @param orderDto 订单信息
     * @return 返回该用户的所有订单
     */
    List<OrderVo> createOrder(OrderDto orderDto);

    /**
     * 删除所选订单
     * @param userId 用户id
     * @param orderIdList 所选订单id数组
     * @return 删除后的所有
     */
    List<OrderVo> deleteOrders(Integer userId, List<String> orderIdList);

    /**
     * 取消订单
     * @param userId 用户id
     * @param orderId 订单id
     * @return 取消后的所有订单（已取消的也展示）
     */
    List<OrderVo> cancelOrder(Integer userId,String orderId);

}
