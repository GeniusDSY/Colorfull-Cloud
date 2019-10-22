package cn.edu.cqupt.mis.colorfullcloud.common.contants;

public interface Status {

    /**
     * 微信所需常量
     * */
    String GRANT_TYPE_KEY = "grant_type";
    String GRANT_TYPE_KEY_VALUE = "authorization_code";
    String APPID_KEY = "appid";
    String MCH_ID = "mch_id";
    String NONCE_STR = "nonce_str";
    String SIGN = "sign";
    String BODY = "body";
    String OUT_TRADE_NO = "out_trade_no";
    String TOTAL_FEE = "total_fee";
    String SPBILL_CREATE_IP ="spbill_create_ip";
    String NOTIFY_URL = "notify_url";
    String SECRET = "secret";
    String JS_CODE = "js_code";
    String ACCESS_TOKEN="access_token";
    String LANG_KEY="lang";
    String LANG_VALUE="zh_CN";
    String MAP_KEY="key";
    String FROM="from";
    String TO="to";
    String ADDRESS="address";
    Integer PAY_SUCCESS = 1;
    Integer EVENT_OFFER = 2;
    Integer CANCEL = 3;

    String INSTITUTION_ICON = "/institution/icon/";
    String INSTITUTION_PICTURE = "/institution/picture";
    String COURSE_ICON = "/course/";

    String TRADE_TYPE = "trade_type";
    String RESULT_CODE = "result_code";
    String OPEN_ID = "openid";
    String TRANSACTION_ID = "transaction_id";
}
