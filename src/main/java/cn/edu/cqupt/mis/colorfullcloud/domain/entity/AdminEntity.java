package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 22:02
 * @desc :
 */
@Data
@ToString
public class AdminEntity {

    @ApiModelProperty(name = "id",value = "管理员id")
    private Integer id;
    @ApiModelProperty(name = "adminId",value = "管理员账号")
    private String adminId;
    @ApiModelProperty(name = "adminName",value = "管理员名称")
    private String adminName;
    @ApiModelProperty(name = "adminPassword",value = "管理员密码")
    private String adminPassword;

}
