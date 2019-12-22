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

    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 类别id
     */
    private Integer categoryId;
    /**
     * 机构id
     */
    private Integer institutionId;
    /**
     * 教师id
     */
    private Integer teacherId;
    /**
     * 教师信息
     */
    private TeacherEntity teacherIntroduction;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程价格
     */
    private Double price;
    /**
     * 课时
     */
    private Integer cycle;
    /**
     * 课程图片
     */
    private String picture;

    /**
     * 课程安排
     */
    private String courseSchedule;
    /**
     * 课程详细介绍
     */
    private String courseIntroduction;
    /**
     * 课程状态(0:正常;1:已满;2:下架)
     */
    private Integer status;



}
