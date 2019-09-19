package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.dao.OrderDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author :DengSiYuan
 * @date :2019/9/15 15:43
 * @desc : 随机编号的生成
 */
@Component
public class UUIDUtil {

    @Resource
    private OrderDao orderDao;

    private static String getRandomString(){
        return String.valueOf(UUID.randomUUID());
    }

    public String getRandomOrderId(){
        String orderId = getRandomString();
        while (orderDao.selectOrderId(orderId) != null){
            orderId = getRandomString();
        }
        return orderId;
    }

}
