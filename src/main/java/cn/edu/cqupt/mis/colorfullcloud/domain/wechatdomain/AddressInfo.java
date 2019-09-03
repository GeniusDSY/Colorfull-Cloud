package cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain;

import lombok.Data;
import lombok.ToString;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 9:15
 * @desc :
 */
@Data
@ToString
public class AddressInfo {

    private String nation;
    private String province;
    private String city;
    private String district;
    private Integer adcode;

}
