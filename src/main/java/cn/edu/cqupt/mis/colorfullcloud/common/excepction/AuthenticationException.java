package cn.edu.cqupt.mis.colorfullcloud.common.excepction;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:54
 * @desc : 权限异常拦截处理
 */
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String msg) {
        super(msg);
    }

}
