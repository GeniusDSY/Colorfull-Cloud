package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 10:56
 * @desc :
 */
@Data
@ToString
@ApiModel(value = "categoryDto",description = "类别传入实体模型")
public class CategoryDto {

    @ApiModelProperty(name = "id",value = "类别id")
    private Integer categoryId;
    @ApiModelProperty(name = "type",value = "类别名称")
    private String type;

}
