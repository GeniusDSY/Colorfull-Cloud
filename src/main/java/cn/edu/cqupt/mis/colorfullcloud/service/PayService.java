package cn.edu.cqupt.mis.colorfullcloud.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author :DengSiYuan
 * @date :2019/10/21 21:20
 * @desc : 支付服务接口
 */
@Service
public interface PayService {

    /**
     * 生成预支付账单
     *
     * @param userId
     * @param totalFee
     * @param spbillCreateIp
     * @return
     */
    Map prePayResult(Integer userId, String orderId,Integer totalFee, String spbillCreateIp);

    /**
     * 更新订单状态
     * @param orderId
     * @param wechatOrderId
     * @return
     */
    Boolean updateOrderStatus(String orderId, String wechatOrderId);
}
