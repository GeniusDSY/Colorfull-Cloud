package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 18:24
 * @desc :
 */
@ApiModel(value = "userVo",description = "用户展示信息")
@Data
@ToString
public class UserVo {

    @ApiModelProperty(name = "id",value = "用户ID(主键、自增）",dataType = "int")
    private Integer userId;
    @ApiModelProperty(name = "nickName",value = "自定义昵称",dataType = "string")
    private String nickName;
    @ApiModelProperty(name = "phone",value = "用户手机号",dataType = "string")
    private String phone;
    @ApiModelProperty(name = "sparePhone",value = "备用手机号",dataType = "string")
    private String sparePhone;
    @ApiModelProperty(name = "country",value = "国家",dataType = "string")
    private String country;
    @ApiModelProperty(name = "province",value = "省份")
    private String province;
    @ApiModelProperty(name = "city",value = "城市")
    private String city;
    @ApiModelProperty(name = "parentName",value = "家长姓名",dataType = "string")
    private String parentName;
    @ApiModelProperty(name = "avatarUrl",value = "头像地址",dataType = "string")
    private String avatarUrl;
    @ApiModelProperty(name = "registerTime",value = "注册时间",dataType = "string")
    private String registerTime;
    @ApiModelProperty(name = "age",value = "年龄",dataType = "integer")
    private Integer age;
    @ApiModelProperty(name = "gender",value = "性别（0未知1男2女）",dataType = "integer")
    private Integer gender;
    @ApiModelProperty(name = "homeAddress",value = "家庭住址",dataType = "string")
    private String homeAddress;
    @ApiModelProperty(name = "receivingAddress",value = "收货地址",dataType = "string")
    private String receivingAddress;


}
