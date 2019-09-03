package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;

import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 9:18
 * @desc :
 */
@Data
@ToString
public class Elements {

    private Location from;
    private Location to;
    private Integer distance;
    private Integer duration;

}
