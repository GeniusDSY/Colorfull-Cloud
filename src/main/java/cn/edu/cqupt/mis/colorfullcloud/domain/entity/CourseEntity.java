package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/8/26 15:19
 * @desc : 课程数据库实体类
 */
@Data
@ToString
public class CourseEntity {

    @ApiModelProperty(value = "课程id")
    private Integer courseId;
    @ApiModelProperty(value = "类别id")
    private Integer categoryId;
    @ApiModelProperty(value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    @ApiModelProperty(value = "课程详细介绍")
    private String introduction;
    @ApiModelProperty(value = "课程状态(0:正常;1:已满;2:下架)")
    private Integer status;



}
