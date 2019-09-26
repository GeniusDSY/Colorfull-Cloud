package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/26 20:42
 * @desc :
 */
@Data
@ToString
public class SuggestionDto {

    @ApiModelProperty(name = "userId",value = "用户id",dataType = "int")
    private Integer userId;
    @ApiModelProperty(name = "introduction",value = "反馈详情",dataType = "string")
    private String introduction;

}
