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

    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 机构id
     */
    private Integer institutionId;
    /**
     * 课程Id
     */
    private Integer courseId;
    private String time;
    private String timeZone;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 某产品课时
     */
    private Integer cycleSum;
    /**
     * 价格
     */
    private Float price;

}
