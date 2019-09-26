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

    /**
     * 管理员id
     */
    private Integer id;
    /**
     * 管理员账号
     */
    private String adminId;
    /**
     * 管理员名称
     */
    private String adminName;
    /**
     * 管理员密码
     */
    private String adminPassword;

}
