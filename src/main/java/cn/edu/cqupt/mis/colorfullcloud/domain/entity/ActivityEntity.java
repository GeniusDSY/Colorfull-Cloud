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

    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动详情
     */
    private String introduction;
    /**
     * 价格
     */
    private Double price;
    /**
     * 产品数量
     */
    private Integer count;
    /**
     * 活动状态(0:正常；1：已过期；2：已爆满)
     */
    private Integer status;

}
