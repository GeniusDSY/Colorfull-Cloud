package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;

import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 9:25
 * @desc :
 */
@Data
public class Location {

    private Double lat;
    private Double lng;

    @Override
    public String toString() {
        return lat + "," + lng;
    }
}
