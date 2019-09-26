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

    /**
     * 用户ID(主键、自增）
     */
    private Integer userId;
    /**
     * 用户微信openid
     */
    @NotNull(message = "openid不能为空")
    private String openid;
    /**
     * 自定义昵称
     */
    private String nickName;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 备用手机号
     */
    private String sparePhone;
    /**
     * 密码（目前无用）
     */
    private String password;
    /**
     * 家长姓名
     */
    private String parentName;
    /**
     * 微信头像链接
     */
    private String avatarUrl;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 注册设备的Mac
     */
    private String registerMac;
    /**
     * 是否可用(0:可用 1:不可用)
     */
    private Integer isDisabled;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别（1男2女）
     */
    private Integer gender;
    /**
     * 家庭住址
     */
    private String homeAddress;
    /**
     * 收货地址
     */
    private String receivingAddress;


}
