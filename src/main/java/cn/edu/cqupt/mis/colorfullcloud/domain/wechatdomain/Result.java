package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 9:13
 * @desc : ip返回的result
 */
@Data
@ToString
public class Result {

    private String ip;
    private Location location;
    private AddressInfo ad_info;
    private List<Elements> elements;
}
