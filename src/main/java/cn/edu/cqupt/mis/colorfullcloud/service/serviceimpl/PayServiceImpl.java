package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.dao.OrderDao;
import cn.edu.cqupt.mis.colorfullcloud.service.PayService;
import cn.edu.cqupt.mis.colorfullcloud.util.ServiceUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.XmlBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @author :DengSiYuan
 * @date :2019/10/22 15:19
 * @desc :
 */
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private XmlBeanUtils xmlBeanUtils;
    @Resource
    private OrderDao orderDao;

    /**
     * 生成预支付账单
     *
     * @param userId
     * @param totalFee
     * @param spbillCreateIp
     * @return
     */
    @Override
    public Map prePayResult(Integer userId,String orderId, String totalFee, String spbillCreateIp) {
        Map<String,String> map = xmlBeanUtils.getPrePayResult(userId,orderId,totalFee,spbillCreateIp);
        for (Object key : map.keySet()) {
            String value = map.get(key);
            log.info("key:{}->value:{}",key,value);
        }
        return map;
    }

    /**
     * 更新订单状态
     * @param request
     * @return
     */
    @Override
    public String updateOrderStatus(HttpServletRequest request) {
        try {
            InputStream inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                //将流转换成字符串
                String result = new String(outStream.toByteArray(), "UTF-8");
                //将字符串解析成XML
                Document doc = DocumentHelper.parseText(result);
                //将XML格式转化成MAP格式数
                Map<String, String> resultMap = XmlBeanUtils.readStringXmlOut(doc.getXMLEncoding());
                //后续具体自己实现
                if ("SUCCESS".equals(resultMap.get("result_code")) && orderDao.selectOrderId(resultMap.get("out_trade_no")).getAmount() == (Float.valueOf(resultMap.get("total_fee"))/ 100)){
                    //通知微信支付系统接收到信息
                    ServiceUtil.checkSqlExecuted(orderDao.modifyOrderSuccessByOrderId(resultMap.get("out_trade_no"),resultMap.get("transaction_id")));
                    return "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //如果失败返回错误，微信会再次发送支付信息
        return "<xml><return_code><![CDATA[FAIL]]></return_code> <return_msg><![CDATA[参数校验错误]]></return_msg></xml>";
    }
}
