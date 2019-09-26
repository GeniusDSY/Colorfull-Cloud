package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/1 8:43
 * @desc :
 */
@Data
@ToString
public class CategoryEntity {

    /**
     * 类别id
     */
    private Integer categoryId;
    /**
     * 类别
     */
    private String type;
}
