package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author :DengSiYuan
 * @date :2019/5/2 13:42
 * @desc :
 */
@Data
@ToString
public class UserEntity {

    @ApiModelProperty(name = "id",value = "用户ID(主键、自增）",dataType = "int")
    private Integer id;
    @NotNull(message = "openid不能为空")
    @ApiModelProperty(name = "openid",value = "用户微信openid",dataType = "string")
    private String openid;
    @ApiModelProperty(name = "nickName",value = "自定义昵称",dataType = "string")
    private String nickName;
    @ApiModelProperty(name = "avatarUrl",value = "微信头像链接")
    private String avatarUrl;
    @ApiModelProperty(name = "phone",value = "用户手机号",dataType = "string")
    private String phone;
    @ApiModelProperty(name = "sparePhone",value = "备用手机号",dataType = "string")
    private String sparePhone;
    @ApiModelProperty(name = "password",value = "密码（目前无用）",dataType = "string")
    private String password;
    @ApiModelProperty(name = "parentName",value = "家长姓名",dataType = "string")
    private String parentName;
    @ApiModelProperty(name = "registerTime",value = "注册时间",dataType = "string")
    private String registerTime;
    @ApiModelProperty(name = "registerMac",value = "注册设备的Mac",dataType = "string")
    private String registerMac;
    @ApiModelProperty(name = "isDisabled",value = "是否可用(0:可用 1:不可用)",dataType = "integer")
    private Integer isDisabled;
    @ApiModelProperty(name = "age",value = "年龄",dataType = "integer")
    private Integer age;
    @ApiModelProperty(name = "gender",value = "性别（1男0女）",dataType = "integer")
    private Integer gender;
    @ApiModelProperty(name = "homeAddress",value = "家庭住址",dataType = "string")
    private String homeAddress;
    @ApiModelProperty(name = "wxNumber",value = "绑定的微信号",dataType = "string")
    private String wxNumber;
    @ApiModelProperty(name = "receivingAddress",value = "收货地址",dataType = "string")
    private String receivingAddress;


}
