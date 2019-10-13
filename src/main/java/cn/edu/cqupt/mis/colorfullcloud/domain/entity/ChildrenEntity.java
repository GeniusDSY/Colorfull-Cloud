package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/24 16:15
 * @desc :
 */
@Data
@ToString
public class ChildrenEntity {

    /**
     * 孩子id
     */
    private Integer childrenId;
    /**
     * 家长id（账号id）
     */
    private Integer userId;
    /**
     * 孩子姓名
     */
    private String childrenName;
    /**
     * 身份证号
     */
    private String card;
    /**
     * 孩子生日
     */
    private String birth;
    /**
     * 孩子性别(0:未知;1:男;2:女)
     */
    private Integer sex;

    /**
     * 孩子学校
     */
    private String school;

    /**
     * 孩子年级
     */
    private String grade;

}
