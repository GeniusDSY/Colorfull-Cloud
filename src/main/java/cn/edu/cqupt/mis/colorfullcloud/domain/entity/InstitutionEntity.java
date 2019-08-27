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
    private Integer phone;

}
