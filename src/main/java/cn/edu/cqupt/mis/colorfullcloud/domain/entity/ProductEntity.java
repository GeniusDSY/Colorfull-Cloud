package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 12:23
 * @desc :
 */
@ApiModel("订单详情")
@Data
@ToString
public class ProductEntity {

    @ApiModelProperty(name = "productId",value = "产品id")
    private Integer productId;
    @ApiModelProperty(name = "orderId",value = "订单编号")
    private String orderId;
    @ApiModelProperty(name = "institutionId",value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(name = "courseId",value = "课程Id")
    private String courseId;
    @ApiModelProperty(name = "count",value = "数量")
    private Integer count;
    @ApiModelProperty(name = "price",value = "价格")
    private Float price;

}
