package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;


import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 18:24
 * @desc : 微信登陆的用户信息
 */
@Data
@ToString
public class OpenId {

    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;

}
