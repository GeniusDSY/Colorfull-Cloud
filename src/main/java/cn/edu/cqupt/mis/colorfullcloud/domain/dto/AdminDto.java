package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 19:30
 * @desc :
 */
@Data
@ToString
@ApiModel(value = "adminDto",description = "管理员传入实体模型")
public class AdminDto {

    @ApiModelProperty(name = "id",value = "管理员id")
    private Integer id;
    @ApiModelProperty(name = "adminId",value = "管理员账号")
    private String adminId;
    @ApiModelProperty(name = "adminPassword",value = "管理员密码")
    private String adminPassword;
    @ApiModelProperty(name = "adminName",value = "管理员名称")
    private String adminName;

}
