package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/1 19:19
 * @desc :
 */
@Data
@ToString
@ApiModel(value = "categoryVo",description = "类别展示模型")
public class CategoryVo {

    @ApiModelProperty(name = "categoryId",value = "类别id")
    private Integer categoryId;
    @ApiModelProperty(name = "type",value = "类别")
    private String type;
    @ApiModelProperty(name = "courseVoList",value = "课程")
    private List<CourseVo> courseVoList;

}
