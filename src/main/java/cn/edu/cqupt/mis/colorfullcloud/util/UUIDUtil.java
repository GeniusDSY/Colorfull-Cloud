package cn.edu.cqupt.mis.colorfullcloud.util;

import java.util.UUID;

/**
 * @author :DengSiYuan
 * @date :2019/9/15 15:43
 * @desc : 随机编号的生成
 */
public class UUIDUtil {

    public static String getRandomString(){
        return String.valueOf(UUID.randomUUID());
    }

}
