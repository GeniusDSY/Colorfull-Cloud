package cn.edu.cqupt.mis.colorfullcloud.common.excepction;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:54
 * @desc : 服务异常处理
 */
public class UploadException extends RuntimeException {

    public UploadException(String msg) {
        super(msg);
    }

}
