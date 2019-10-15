package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @author :DengSiYuan
 * @date :2019/8/26 11:36
 * @desc : 机构数据库实体类
 *
 */
@Data
@ToString
public class InstitutionEntity {

    @ApiModelProperty(value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(value = "机构名称")
    private String institutionName;
    @ApiModelProperty(value = "机构地址")
    private String address;
    @ApiModelProperty(value = "联系人")
    private String leader;
    @ApiModelProperty(value = "联系方式")
    private String phone;
    @ApiModelProperty(value = "机构icon地址")
    private String icon;
    @ApiModelProperty(value = "机构详细介绍")
    private String introduction;
    @ApiModelProperty(value = "经度")
    private Double lng;
    @ApiModelProperty(value = "纬度")
    private Double lat;
    @ApiModelProperty(value = "距离")
    private Integer distance;

}
