package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/24 21:11
 * @desc :
 */
@Data
@ToString
public class ActivityChildrenEntity {

    /**
     * 孩子活动订单id
     */
    private Integer id;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 孩子身份证号
     */
    private String childrenCard;

}
