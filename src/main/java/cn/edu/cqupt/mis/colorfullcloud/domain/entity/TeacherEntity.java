package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/22 15:46
 * @desc : 教师数据库实体类
 */
@Data
@ToString
public class TeacherEntity {

    @ApiModelProperty(name = "teacherId",value = "教师id")
    private Integer teacherId;
    @ApiModelProperty(name = "teacherName",value = "教师姓名")
    private String teacherName;
    @ApiModelProperty(name = "phone",value = "教师联系方式")
    private String phone;
    @ApiModelProperty(name = "introduction",value = "教师介绍",allowEmptyValue = true)
    private String introduction;

}
