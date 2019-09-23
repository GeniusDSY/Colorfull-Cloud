package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/22 21:07
 * @desc : 活动展示层
 */
@Data
@ToString
@ApiModel(value = "activityVo",description = "活动展示模型")
public class ActivityVo {

    @ApiModelProperty(name = "activityId",value = "活动id")
    private Integer activityId;
    @ApiModelProperty(name = "activityName",value = "活动名称")
    private String activityName;
    @ApiModelProperty(name = "introduction",value = "活动详情")
    private String introduction;
    @ApiModelProperty(name = "price",value = "价格")
    private Double price;
    @ApiModelProperty(name = "status",value = "活动状态(0:正常；1：已过期；2：已爆满)")
    private Integer status;

}
