package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author :DengSiYuan
 * @date :2019/9/15 15:33
 * @desc :
 */
@Data
@ToString
@ApiModel(value = "productDto",description = "产品输入实体模块")
public class ProductDto {

    @ApiModelProperty(name = "orderId",value = "订单编号")
    private String orderId;
    @ApiModelProperty(name = "institutionId",value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(name = "courseId",value = "课程id")
    private Integer courseId;
    @ApiModelProperty(name = "time",value = "上课时间")
    private String time;
    @ApiModelProperty(name = "timeZone",value = "上下午")
    private String timeZone;
    @ApiModelProperty(name = "count",value = "数量")
    private Integer count;
    @ApiModelProperty(name = "cycleSum",value = "课时")
    private Integer cycleSum;
    @ApiModelProperty(name = "price",value = "价格总额")
    private Float price;

}
