package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author :DengSiYuan
 * @date :2019/5/3 20:28
 * @desc :
 */
@Data
@ToString
@ApiModel(value = "userDto",description = "用户提交信息")
public class UserDto {

    @NotNull(message = "用户唯一标识为空")
    @ApiModelProperty(name = "userId",value = "用户唯一标识")
    private Integer userId;
    @ApiModelProperty(name = "nickName",value = "微信名")
    private String nickName;
    @ApiModelProperty(name = "country",value = "国家",dataType = "string")
    private String country;
    @ApiModelProperty(name = "province",value = "省份",dataType = "string")
    private String province;
    @ApiModelProperty(name = "city",value = "城市",dataType = "string")
    private String city;
    @ApiModelProperty(name = "avatarUrl",value = "微信头像链接")
    private String avatarUrl;
    @ApiModelProperty(name = "gender",value = "微信登记性别")
    private Integer gender;
    @ApiModelProperty(name = "phone",value = "用户手机号",dataType = "string")
    private String phone;
    @ApiModelProperty(name = "sparePhone",value = "备用手机号",dataType = "string")
    private String sparePhone;
    @ApiModelProperty(name = "password",value = "密码（目前无用）",dataType = "string")
    private String password;
    @ApiModelProperty(name = "parentName",value = "家长姓名",dataType = "string")
    private String parentName;
    @ApiModelProperty(name = "age",value = "年龄",dataType = "integer")
    private Integer age;
    @ApiModelProperty(name = "homeAddress",value = "家庭住址",dataType = "string")
    private String homeAddress;
    @ApiModelProperty(name = "receivingAddress",value = "收货地址",dataType = "string")
    private String receivingAddress;

}
