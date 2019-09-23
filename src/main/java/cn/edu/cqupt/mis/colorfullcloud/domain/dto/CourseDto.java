package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 10:33
 * @desc :
 */
@Data
@ToString
@ApiModel("课程传入实体模型")
public class CourseDto {

    @ApiModelProperty(value = "课程id(创建课程时可不传)")
    private Integer courseId;
    @ApiModelProperty(value = "类别id")
    private Integer categoryId;
    @ApiModelProperty(value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(value = "教师信息")
    private TeacherEntity teacherIntroduction;
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "课程价格")
    private Double price;
    @ApiModelProperty(value = "课程详细介绍")
    private String introduction;
    @ApiModelProperty(value = "课程状态(0:正常;1:已满;2:下架)")
    private Integer status;

}
