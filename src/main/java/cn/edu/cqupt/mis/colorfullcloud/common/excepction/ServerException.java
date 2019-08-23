package cn.edu.cqupt.mis.colorfullcloud.common.excepction;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:54
 * @desc : 服务异常处理
 */
public class ServerException extends RuntimeException {

    public ServerException(String msg) {
        super(msg);
    }

}
