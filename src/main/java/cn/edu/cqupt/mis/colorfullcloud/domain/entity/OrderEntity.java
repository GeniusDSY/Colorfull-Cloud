package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/8/26 9:44
 * @desc : 订单数据库对应实体类
 */
@Data
@ToString
public class OrderEntity {

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 下单时间
     */
    private String orderTime;
    /**
     * 支付方式(1:微信;2:支付宝;3:银行卡)
     */
    private Integer payment;
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 支付编号
     */
    private String payId;
    /**
     * 总额
     */
    private Float total;
    /**
     * 折扣减免
     */
    private Float discount;
    /**
     * 实际支付
     */
    private Float amount;
    /**
     * 订单状态(0:未支付;1:支付成功)
     */
    private Integer status;
    /**
     * 活动id
     */
    private Integer activityId;

}
