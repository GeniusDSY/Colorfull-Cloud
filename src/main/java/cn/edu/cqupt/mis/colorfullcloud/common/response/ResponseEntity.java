package cn.edu.cqupt.mis.colorfullcloud.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@ApiModel
@Slf4j
public class ResponseEntity<T> {
    @ApiModelProperty("1 成功; 2  参数错误; 3  " +
            "第三方服务错误;  4  服务器运行错误;  5  用户操作错误") private int status;
    @ApiModelProperty("提示信息") private String msg;
    @ApiModelProperty("业务数据") private T object;

    public ResponseEntity(int status, String msg, T object) {
        this.status = status;
        this.msg = msg;
        this.object = object;
        log.error(status + msg + object);
    }
}
