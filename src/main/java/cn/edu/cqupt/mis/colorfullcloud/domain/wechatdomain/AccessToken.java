package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;

import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/8/19 21:04
 * @desc :
 */
@Data
@ToString
public class AccessToken {

    private String access_token;
    private Integer expires_in;
    private Integer errcode;
    private String errmsg;

}
