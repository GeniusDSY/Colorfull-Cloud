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

    /**
     * 教师id
     */
    private Integer teacherId;
    /**
     * 教师所属机构id
     */
    private Integer institutionId;
    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 教师联系方式
     */
    private String phone;
    /**
     * 教师介绍
     */
    private String introduction;

}
