package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;

import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 9:11
 * @desc : 腾讯地图返回数据
 */
@Data
@ToString
public class TencentMapResult {

    private Integer status;
    private String message;
    private Result result;
}
