package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import io.swagger.annotations.ApiModel;
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
@ApiModel("教师传入实体模型")
public class TeacherDto {

    @ApiModelProperty(name = "teacherId",value = "教师id")
    private Integer teacherId;
    @ApiModelProperty(name = "institutionId",value = "教师所属机构id")
    private Integer institutionId;
    @ApiModelProperty(name = "teacherName",value = "教师姓名")
    private String teacherName;
    @ApiModelProperty(name = "phone",value = "教师联系方式")
    private String phone;
    @ApiModelProperty(name = "introduction",value = "教师介绍",allowEmptyValue = true)
    private String introduction;

}
