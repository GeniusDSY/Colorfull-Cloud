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
@Data
@ToString
@ApiModel(value = "orderDto",description = "订单输入实体模块")
public class OrderDto {

    @ApiModelProperty(name ="userId",value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "orderCycleSum",value = "订单课时总长")
    private Integer orderCycleSum;
    @ApiModelProperty(name = "total",value = "价格总额")
    private Float total;
    @ApiModelProperty(name="discount",value = "折扣总额")
    private Float discount;
    @ApiModelProperty(name = "amount",value = "实际支付金额")
    private Float amount;
    @ApiModelProperty(name = "productDtoList",value = "订单详情列表")
    private List<ProductDto> productDtoList;
    @ApiModelProperty(name = "status",value = "订单状态(0：正常订单；1：活动订单)")
    private Integer status;
    @ApiModelProperty(name = "activityId",value = "活动id")
    private Integer activityId;
    @ApiModelProperty(name = "childrenCard",value = "孩子身份证号")
    private String childrenCard;

}
