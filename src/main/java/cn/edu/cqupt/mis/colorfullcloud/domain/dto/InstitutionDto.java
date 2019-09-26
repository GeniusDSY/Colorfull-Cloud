package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/22 22:03
 * @desc :
 */
@Data
@ToString
@ApiModel(value = "institutionDto",description = "机构传入实体模型")
public class InstitutionDto {

    @ApiModelProperty(name = "institutionId",value = "机构id（创建时可不传）")
    private Integer institutionId;
    @ApiModelProperty(name = "institutionName",value = "机构名称")
    private String institutionName;
    @ApiModelProperty(name = "address",value = "机构地址")
    private String address;
    @ApiModelProperty(name = "leader",value = "联系人")
    private String leader;
    @ApiModelProperty(name = "phone",value = "联系方式")
    private Integer phone;
    @ApiModelProperty(name = "introduction",value = "机构详细介绍",dataType = "introduction")
    private String introduction;

}
