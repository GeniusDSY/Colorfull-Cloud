package cn.edu.cqupt.mis.colorfullcloud.common.excepction;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:54
 * @desc : 第三方异常处理
 */
public class ThirdPartyServiceException extends RuntimeException {

    public ThirdPartyServiceException(String msg) {
        super(msg);
    }
}
