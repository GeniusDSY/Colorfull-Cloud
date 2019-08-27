package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author :DengSiYuan
 * @date :2019/8/26 9:44
 * @desc : 订单数据库对应实体类
 */
@Data
@ToString
public class OrderEntity {

    @ApiModelProperty(value = "订单编号")
    private String orderId;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(value = "下单时间")
    private Date orderTime;
    @ApiModelProperty(value = "支付方式(1:微信;2:支付宝;3:银行卡)")
    private Integer payment;
    @ApiModelProperty(value = "支付时间")
    private Date payTime;
    @ApiModelProperty(value = "支付编号")
    private String payId;
    @ApiModelProperty(value = "总额")
    private Float total;
    @ApiModelProperty(value = "折扣减免")
    private Float discount;
    @ApiModelProperty(value = "实际支付")
    private Float amount;
    @ApiModelProperty(value = "订单状态(0:未支付;1:优惠转发中;2:支付成功)")
    private Integer status;

}
