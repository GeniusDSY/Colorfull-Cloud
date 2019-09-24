package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

public class ServiceUtil {

    public static ResponseEntity checkServerExecuteFailResponseEntity(boolean flag) {
        if (!flag) {
            return new ResponseEntity<>(ResponseStatu.SERVER_EXCUTE_FAIL, "服务器错误", null);
        }
        return new ResponseEntity<>( ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    public static ResponseEntity checkThreeServiceFailResponseEntity(boolean flag, String msg){
        if (!flag) {
            return new ResponseEntity<>(ResponseStatu.THREE_SERVICE_FILE, msg, null);
        }
        return new ResponseEntity<>( ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    public static ResponseEntity checkAuthenticationFailResponseEntity(boolean flag, String msg) {
        if (!flag) {
            return new ResponseEntity<>(ResponseStatu.AUTHENTATION_FIAL, msg, null);
        }
        return new ResponseEntity<>( ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    public static ResponseEntity checkUserOperationFailResponseEntity(boolean flag, String msg) {
        if (!flag) {
            return new ResponseEntity<>(ResponseStatu.USER_OPERATION_ERROR, msg, null);
        }
        return new ResponseEntity<>( ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    public static ResponseEntity serverExecuteFailResponseEntity() {
        return new ResponseEntity<>(ResponseStatu.SERVER_EXCUTE_FAIL, "响应服务器错误", null);
    }

    public static ResponseEntity threeServiceFailResponseEntity(String msg){
        return new ResponseEntity<>(ResponseStatu.THREE_SERVICE_FILE, msg, null);
    }

    public static ResponseEntity authentationFailResponseEntity(String msg) {
        return new ResponseEntity<>(ResponseStatu.AUTHENTATION_FIAL, msg, null);
    }

    public static ResponseEntity userOperationFailResponseEntity(String msg) {
        return new ResponseEntity<>(ResponseStatu.USER_OPERATION_ERROR, msg, null);
    }

    public static void checkSqlExecuted(boolean... flags) {
        for (boolean flag : flags) {
            if (!flag) {
                throw new ServerException("数据库事务错误");
            }
        }

    }

    public static <T> void checkSqlExecuted(boolean flag, T t, Consumer<T> consumer) {
        if (!flag) {
            consumer.accept(t);
            throw new ServerException("服务器错误");
        }
    }

}
