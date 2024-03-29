package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ChildrenEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 11:02
 * @desc :
 */
@ApiModel(value = "orderVo",description = "订单展示信息")
@Data
@ToString
public class OrderVo {

    @ApiModelProperty(name = "orderId",value = "订单编号",dataType = "int")
    private String orderId;
    @ApiModelProperty(name = "userId",value = "用户id",dataType = "int")
    private Integer userId;
    @ApiModelProperty(name = "orderProductList",value = "订单详情",dataType = "list")
    private List<OrderInstitutionVo> orderProductList;
    @ApiModelProperty(name = "childrenCard",value = "孩子身份证号",dataType = "string")
    private String childrenCard;
    @ApiModelProperty(name = "children",value = "孩子信息")
    private ChildrenEntity childrenEntity;
    @ApiModelProperty(name = "remainTime",value = "剩余课时",dataType = "int")
    private Integer remainTime;
    @ApiModelProperty(name = "orderTime",value = "下单时间",dataType = "string")
    private String orderTime;
    @ApiModelProperty(name = "payment",value = "支付方式(1:微信;2:支付宝;3:银行卡)",dataType = "string")
    private String payment;
    @ApiModelProperty(name = "payTime",value = "支付时间",dataType = "date")
    private String payTime;
    @ApiModelProperty(name = "orderCycleSum",value = "订单课时总长")
    private Integer orderCycleSum;
    @ApiModelProperty(name = "total",value = "总额",dataType = "float")
    private Float total;
    @ApiModelProperty(name = "discount",value = "折扣减免",dataType = "float")
    private Float discount;
    @ApiModelProperty(name = "amount",value = "实际支付",dataType = "float")
    private Float amount;
    @ApiModelProperty(name = "status",value = "订单状态(0:未支付;1:活动中;2:已支付;3:订单取消)",dataType = "int")
    private Integer status;
    @ApiModelProperty(name = "activityId",value = "订单种类",dataType = "int")
    private Integer activityId;

}
