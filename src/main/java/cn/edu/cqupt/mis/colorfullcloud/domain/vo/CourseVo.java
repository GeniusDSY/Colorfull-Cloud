package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/8/26 23:10
 * @desc :
 */
@ApiModel(value = "courseVo",description = "课程展示信息")
@Data
@ToString
public class CourseVo {

    @ApiModelProperty(name = "courseId",value = "课程id",dataType = "int")
    private Integer courseId;
    @ApiModelProperty(name = "courseName",value = "课程名称",dataType = "string")
    private String courseName;
    @ApiModelProperty(name = "teacherIntroduction",value = "教师信息")
    private TeacherEntity teacherIntroduction;
    @ApiModelProperty(name ="price",value = "课程价格",dataType = "double")
    private Double price;
    @ApiModelProperty(name = "introduction",value = "课程详细介绍",dataType = "string")
    private String introduction;
    @ApiModelProperty(name = "status",value = "课程状态(0:正常;1:已满;2:下架)",dataType = "int")
    private Integer status;


}
