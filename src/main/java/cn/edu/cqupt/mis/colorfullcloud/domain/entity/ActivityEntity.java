package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/22 16:07
 * @desc : 活动详情
 */
@Data
@ToString
public class ActivityEntity {

    @ApiModelProperty(name = "activityId",value = "活动id")
    private Integer activityId;
    @ApiModelProperty(name = "activityName",value = "活动名称")
    private String activityName;
    @ApiModelProperty(name = "introduction",value = "活动详情")
    private String introduction;
    @ApiModelProperty(name = "price",value = "价格")
    private Double price;
    @ApiModelProperty(name = "count",value = "产品数量")
    private Integer count;
    @ApiModelProperty(name = "status",value = "活动状态(0:正常；1：已过期；2：已爆满)")
    private Integer status;

}
