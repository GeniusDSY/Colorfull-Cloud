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
@ApiModel(value = "courseDto",description = "课程传入实体模型")
public class CourseDto {

    @ApiModelProperty(name = "courseId",value = "课程id(创建课程时可不传)")
    private Integer courseId;
    @ApiModelProperty(name = "categoryId",value = "类别id")
    private Integer categoryId;
    @ApiModelProperty(name = "institutionId",value = "机构id")
    private Integer institutionId;
    @ApiModelProperty(name = "teacherId",value = "教师id")
    private Integer teacherId;
    @ApiModelProperty(name = "teacherIntroduction",value = "教师信息")
    private TeacherEntity teacherIntroduction;
    @ApiModelProperty(name = "course",value = "课程名称")
    private String courseName;
    @ApiModelProperty(name = "price",value = "课程价格")
    private Double price;
    @ApiModelProperty(name = "cycle",value = "课时")
    private Integer cycle;
    @ApiModelProperty(name = "courseSchedule",value = "课程安排",dataType = "string")
    private String courseSchedule;
    @ApiModelProperty(name = "courseIntroduction",value = "课程详细介绍")
    private String courseIntroduction;
    @ApiModelProperty(name = "status",value = "课程状态(0:正常;1:活动课程)")
    private Integer status;

}
