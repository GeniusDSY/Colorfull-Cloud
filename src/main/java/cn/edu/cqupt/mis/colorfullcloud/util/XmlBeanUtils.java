package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author :DengSiYuan
 * @date :2019/10/21 22:21
 * @desc :
 */
@Slf4j
@Component
public class XmlBeanUtils {

    @Resource
    private UserDao userDao;
    @Value("${wechat.pay}")
    private String prePayUrl;
    @Value("${wechat.merchant_id}")
    private String merchantId;
    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.body}")
    private String body;
    @Value("${wechat.notify_url}")
    private String notifyUrl;
    @Value("${wechat.trade_type}")
    private String tradeType;
    @Value("${wechat.merchant_secret}")
    private String secret;


    public Map<String,String> getPrePayResult(Integer userId,String orderId, String totalFee, String SPBillCreateIp){
        SortedMap<String ,String> map = new TreeMap<>();
        map.put(Status.APPID_KEY,appid);
        map.put(Status.MCH_ID,merchantId);
        map.put(Status.NONCE_STR,UUIDUtil.getRandomString());
        map.put(Status.BODY,body);
        map.put(Status.OUT_TRADE_NO,orderId);
        map.put(Status.TOTAL_FEE,totalFee);
        map.put(Status.SPBILL_CREATE_IP,SPBillCreateIp);
        map.put(Status.NOTIFY_URL,notifyUrl);
        map.put(Status.TRADE_TYPE,tradeType);
        map.put(Status.OPEN_ID,userDao.selectUserById(userId).getOpenid());
        //生成签名
        map.put(Status.SIGN,UUIDUtil.createSign(map, secret));
        String requestParam = map2XmlString(map);
        log.info("微信预支付请求xml -> {}",requestParam);
        String result = HttpClientUtil.doPostJson(prePayUrl,requestParam);
        Map<String,String> resultMap = readStringXmlOut(result);
        System.out.println("预支付返回-------");
        for (Object key : resultMap.keySet()) {
            String value = resultMap.get(key);
            System.out.println(key + " : " + value);
        }
        if(StringUtils.equals(Response.FAIL,map.get(Status.RESULT_CODE) )){  //返回报错
            throw  new ServerException(map.get("err_code"));
        }
        //进行二次签名
        SortedMap<String,String> secondMap = new TreeMap<>();
        secondMap.put("appId",appid);
        secondMap.put(Status.TIMESTAMP,System.currentTimeMillis()/1000 +"");
        secondMap.put("nonceStr",UUIDUtil.getRandomString());
        secondMap.put(Status.PACKAGE,"prepay_id="+resultMap.get("prepay_id"));
        secondMap.put(Status.SIGNTYPE,"MD5");
        secondMap.put(Status.PAYSIGN,UUIDUtil.createSign(secondMap, secret));
        return secondMap;
    }

    private String map2XmlString(Map<String, String> map) {
        String xmlResult = "";
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (String key : map.keySet()) {
            //String value = "<![CDATA[" + map.get(key) + "]]>";
            sb.append("<" + key + ">" + map.get(key)/*value*/ + "</" + key + ">");
        }
        sb.append("</xml>");
        xmlResult = sb.toString();
        return xmlResult;
    }

    public static Map<String, String> readStringXmlOut(String xml) {
        Map<String, String> map = new HashMap<>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            @SuppressWarnings("unchecked")
            List<Element> list = rootElt.elements();// 获取根节点下所有节点
            for (Element element : list) { // 遍历节点
                map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, String> readStringXml(String xml) {
        Map<String, String> map = new HashMap<>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            @SuppressWarnings("unchecked")
            List<Element> list = rootElt.elements();// 获取根节点下所有节点
            for (Element element : list) { // 遍历节点
                map.put(element.getName(), element.getText().substring(8,element.getText().length()-2)); // 节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map doXMLParse(String strxml) throws Exception {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        org.jdom2.Document doc = builder.build(in);
        org.jdom2.Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            org.jdom2.Element e = (org.jdom2.Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            System.out.println("放入map--"+k + ":" + v);
            m.put(k, v);
        }
        //关闭流
        in.close();
        return m;
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                org.jdom2.Element e = (org.jdom2.Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

}
