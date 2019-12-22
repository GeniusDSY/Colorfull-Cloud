package cn.edu.cqupt.mis.colorfullcloud.domain.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/26 20:42
 * @desc :
 */
@Data
@ToString
public class SuggestionEntity {

    /**
     * 反馈id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 反馈详情
     */
    private String introduction;

}
