package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.dao.OrderDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author :DengSiYuan
 * @date :2019/9/15 15:43
 * @desc : 随机编号的生成
 */
@Component
public class UUIDUtil {

    @Resource
    private OrderDao orderDao;

    public static String getRandomString(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getRandomOrderId(){
        String orderId = getRandomString();
        while (orderDao.selectOrderId(orderId) != null){
            orderId = getRandomString();
        }
        return orderId;
    }

    public static String createSign(SortedMap<String,String> parameters,String secret){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + secret); //key为商户平台设置的密钥key
        String sign = EncryptionUtil.md5(sb.toString()).toUpperCase();
        return sign;
    }

}
