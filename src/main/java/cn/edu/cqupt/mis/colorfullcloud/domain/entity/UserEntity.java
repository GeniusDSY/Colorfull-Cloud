package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author :DengSiYuan
 * @date :2019/5/2 13:42
 * @desc : 用户数据库实体类
 */
@Data
@ToString
public class UserEntity {

    @ApiModelProperty(value = "用户ID(主键、自增）")
    private Integer userId;
    @NotNull(message = "openid不能为空")
    @ApiModelProperty(value = "用户微信openid")
    private String openid;
    @ApiModelProperty(value = "自定义昵称")
    private String nickName;
    @ApiModelProperty(value = "用户手机号")
    private String phone;
    private String country;
    private String province;
    private String city;
    @ApiModelProperty(value = "备用手机号")
    private String sparePhone;
    @ApiModelProperty(value = "密码（目前无用）")
    private String password;
    @ApiModelProperty(value = "家长姓名")
    private String parentName;
    @ApiModelProperty(value = "微信头像链接")
    private String avatarUrl;
    @ApiModelProperty(value = "注册时间")
    private String registerTime;
    @ApiModelProperty(value = "注册设备的Mac")
    private String registerMac;
    @ApiModelProperty(value = "是否可用(0:可用 1:不可用)")
    private Integer isDisabled;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别（1男2女）")
    private Integer gender;
    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;
    @ApiModelProperty(value = "收货地址")
    private String receivingAddress;


}
