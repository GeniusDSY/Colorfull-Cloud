package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 19:08
 * @desc :
 */
@Data
@ToString
@ApiModel("管理员展示实体模型")
public class AdminVo {

    @ApiModelProperty(name = "id",value = "管理员id")
    private Integer id;
    @ApiModelProperty(name = "adminId",value = "管理员账号")
    private String adminId;
    @ApiModelProperty(name = "adminName",value = "管理员名称")
    private String adminName;

}
