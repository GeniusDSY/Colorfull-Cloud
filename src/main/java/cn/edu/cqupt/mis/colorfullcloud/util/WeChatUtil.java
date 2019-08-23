package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.WeChatServerException;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.AccessToken;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.OpenId;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :DengSiYuan
 * @date :2019/8/15 21:55
 * @desc :微信接口工具类
 */
@Slf4j
@Component
public class WeChatUtil {


    @Value("${wechat.appid}")
    private  String appid;
    @Value("${wechat.secret}")
    private String secret;
    @Value("${wechat.code}")
    private String userInfoUrl;
    @Value("${wechat.access_token}")
    private String accessTokenUrl;

    /**
     * 微信登录API调用以及获取openid
     * @param code
     * @return weChatUserDto
     * */
    public OpenId getOpenId(String code){
        Map<String,String> params = new HashMap<>();
        params.put(Status.APPID_KEY,appid);
        params.put(Status.SECRET,secret);
        params.put(Status.JS_CODE,code);
        params.put(Status.GRANT_TYPE_KEY, Status.GRANT_TYPE_KEY_VALUE);
        String result = HttpClientUtil.doGet(userInfoUrl,params);
        OpenId weChatUserDto = JSON.parseObject(result, OpenId.class);
        if(weChatUserDto.getErrcode() != null || weChatUserDto.getOpenid() == null){
            log.error("调用微信小程序接口出现异常：",weChatUserDto.getErrmsg());
            throw new WeChatServerException("获取微信信息失败！稍后重试！");
        }
        return weChatUserDto;
    }

    public AccessToken getAccessToken(){
        Map<String,String> params = new HashMap<>();
        params.put(Status.GRANT_TYPE_KEY,Status.GRANT_TYPE_KEY_VALUE);
        params.put(Status.APPID_KEY,appid);
        params.put(Status.SECRET,secret);
        String result = HttpClientUtil.doGet(accessTokenUrl,params);
        AccessToken accessToken = JSON.parseObject(result, AccessToken.class);
        if(accessToken.getErrcode() != null || accessToken.getAccess_token() == null){
            log.error("调用微信小程序接口出现异常：",accessToken.getErrmsg());
            throw new WeChatServerException("获取微信信息失败！请联系管理员！");
        }
        return accessToken;
    }

}
