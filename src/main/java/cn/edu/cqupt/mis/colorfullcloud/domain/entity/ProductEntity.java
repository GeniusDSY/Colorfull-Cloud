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
public class OrderProductEntity {

    @ApiModelProperty(name = "institutionId",value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(name = "courseName",value = "课程名称")
    private String courseName;
    private Integer amount;
    private Float price;

}
