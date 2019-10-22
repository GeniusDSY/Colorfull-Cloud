package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/10/21 21:28
 * @desc :
 */
@Data
@ToString
@ApiModel(value = "payResult",description = "预支付调起返回结果实体")
public class PayResult {

    @ApiModelProperty(name = "return_code",value = "返回状态码",dataType = "string")
    private String return_code;
    @ApiModelProperty(name = "return_msg",value = "返回信息",dataType = "string",allowEmptyValue = true)
    private String return_msg;


}
