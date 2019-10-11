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
    String MAP_KEY="key";
    String FROM="from";
    String TO="to";
    Integer PAY_SUCCESS = 1;
    Integer EVENT_OFFER = 2;
    Integer CANCEL = 3;

    String INSTITUTION_ICON = "/institution/icon/";
    String INSTITUTION_PICTURE = "/institution/picture";
    String COURSE_ICON = "/course/";

}
