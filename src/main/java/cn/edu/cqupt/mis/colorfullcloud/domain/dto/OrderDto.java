package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 23:45
 * @desc : 订单传输实体类
 */
@ApiModel
@Data
@ToString
public class OrderDto {

    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "orderProductList",value = "订单详情",dataType = "list")
    private List<InstitutionVo> orderProductList;
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
