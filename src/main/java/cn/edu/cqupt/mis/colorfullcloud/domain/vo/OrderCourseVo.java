package cn.edu.cqupt.mis.colorfullcloud.domain.vo;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/7 8:45
 * @desc : 订单课程展示
 */
@ApiModel(value = "orderCourseVo",description = "订单课程信息")
@Data
@ToString
public class OrderCourseVo {

    @ApiModelProperty(name = "courseId",value = "课程id",dataType = "int")
    private Integer courseId;
    @ApiModelProperty(name = "courseName",value = "课程名称",dataType = "string")
    private String courseName;
    @ApiModelProperty(name = "teacherIntroduction",value = "教师信息")
    private TeacherEntity teacherIntroduction;
    @ApiModelProperty(name ="price",value = "课程价格",dataType = "double")
    private Double price;
    @ApiModelProperty(name = "count",value = "数量",dataType = "int")
    private Integer count;
    @ApiModelProperty(name = "cycle",value = "课时",dataType = "int")
    private Integer cycle;
    @ApiModelProperty(name = "introduction",value = "课程详细介绍",dataType = "string")
    private String introduction;
    @ApiModelProperty(name = "status",value = "课程状态(0:正常;1:已满;2:下架)",dataType = "int")
    private Integer status;

}
