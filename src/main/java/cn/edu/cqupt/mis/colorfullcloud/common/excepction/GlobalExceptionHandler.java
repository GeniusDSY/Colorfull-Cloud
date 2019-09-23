package cn.edu.cqupt.mis.colorfullcloud.common.excepction;

import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:54
 * @desc : 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationExceptionHandler(AuthenticationException e) {
        log.error("AuthenticationException->{}",e);
        return new ResponseEntity<>(ResponseStatu.AUTHENTATION_FIAL, e.getMessage(), null);
    }

    @ExceptionHandler(ThirdPartyServiceException.class)
    public ResponseEntity thirdPartyServiceException(ThirdPartyServiceException e) {
        log.error("ThirdPartyServiceException->{}",e);
        return new ResponseEntity<>(ResponseStatu.THREE_SERVICE_FILE, e.getMessage(), null);
    }

    @ExceptionHandler(ParameterException.class)
    public ResponseEntity parameterExceptionHandler(ParameterException e) {
        log.error("ParameterException->{}",e);
        return new ResponseEntity<>(ResponseStatu.PARMETER_INVALIATE, e.getMessage(), null);
    }

    @ExceptionHandler(ProcessException.class)
    public ResponseEntity processExceptionHandler(ProcessException e) {
        log.error("ProcessException->{}",e);
        return new ResponseEntity<>(ResponseStatu.USER_OPERATION_ERROR, e.getMessage(), null);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity sqlExecutedExceptionHandler(ServerException e) {
        log.error("ServerException->{}",e);
        return new ResponseEntity<>(ResponseStatu.SERVER_EXCUTE_FAIL, e.getMessage(), null);
    }


}
