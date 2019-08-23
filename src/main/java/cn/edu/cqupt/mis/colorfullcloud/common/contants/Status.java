package cn.edu.cqupt.mis.colorfullcloud.common.contants;

/**
 * @author 10587
 */
public interface Status {

    /**
     * 微信所需常量
     * */
    String GRANT_TYPE_KEY = "grant_type";
    String GRANT_TYPE_KEY_VALUE = "authorization_code";
    String APPID_KEY = "appid";
    String SECRET = "secret";
    String JS_CODE = "js_code";
    String ACCESS_TOKEN="access_token";
    String LANG_KEY="lang";
    String LANG_VALUE="zh_CN";
    /**
     * 设置登录状态的key
     */
    String LOGIN_KEY = "login";

    /**
     * 登录状态为已登录的值
     */
    String LOGIN_ATTRIBUTE = "isLogined";

    /**
     * 登录验证码key
     */
    String CODE_KEY = "verifyCode";

    /**
     * session存放userId 的key
     */
    String USER_ID_KEY = "user";

    /**
     * 注册存放邮箱验证码的key
     */
    String MAIL_CODE_KEY = "mailCode";


}
