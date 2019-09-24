package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/24 16:15
 * @desc :
 */
@Data
@ToString
public class ChildrenEntity {

    @ApiModelProperty(name = "childrenId",value = "孩子id")
    private Integer childrenId;
    @ApiModelProperty(name = "userId",value = "家长id（账号id）")
    private Integer userId;
    @ApiModelProperty(name = "childrenName",value = "孩子姓名")
    private String childrenName;
    @ApiModelProperty(name = "card",value = "身份证号")
    private String card;
    @ApiModelProperty(name = "birth",value = "孩子生日")
    private String birth;
    @ApiModelProperty(name = "sex",value = "孩子性别(0:未知;1:男;2:女)")
    private Integer sex;

}
