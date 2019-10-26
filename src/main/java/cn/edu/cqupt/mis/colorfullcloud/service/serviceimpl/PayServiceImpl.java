package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ThirdPartyServiceException;
import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.dao.OrderDao;
import cn.edu.cqupt.mis.colorfullcloud.service.PayService;
import cn.edu.cqupt.mis.colorfullcloud.util.ServiceUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.XmlBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
    @Value("${wechat.merchant_id}")
    private String merchantId;

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
    public void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) {
        String errCode = "";
        String returnXml = "";
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
                System.out.println("转换成字符串结果---"+result);
                //将XML格式转化成MAP格式数
                Map<String, String> resultMap = XmlBeanUtils.doXMLParse(result);
                for (Object key : resultMap.keySet()) {
                    String value = resultMap.get(key);
                    System.out.println(key + " : " + value);
                }
                String returnCode = resultMap.get(Status.RETURN_CODE);
                String mchId = resultMap.get(Status.MCH_ID);
                String resultCode = resultMap.get(Status.RESULT_CODE);
                float totalFee = Float.valueOf(resultMap.get(Status.TOTAL_FEE)) / 100;
                String orderId = resultMap.get(Status.OUT_TRADE_NO);
                String transactionId = resultMap.get(Status.TRANSACTION_ID);
                String payTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new SimpleDateFormat("yyyyMMddHHmmss").parse(resultMap.get(Status.TIME_END)));
                //后续具体自己实现
                if (Response.SUCCESS.equals(returnCode) && mchId.equals(merchantId) && Response.SUCCESS.equals(resultCode)
                        && orderDao.selectOrderId(orderId) != null && orderDao.selectOrderId(orderId).getAmount() == totalFee){
                    //通知微信支付系统接收到信息
                    ServiceUtil.checkSqlExecuted(orderDao.modifyOrderSuccessByOrderId(orderId,transactionId,payTime));
                    log.info("支付回调函数判断结果->{}",resultCode);
                    returnXml= "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";
                }else {
                    //如果失败返回错误，微信会再次发送支付信息
                    returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code> <return_msg><![CDATA[参数校验错误]]></return_msg></xml>";
                    throw new ThirdPartyServiceException("微信支付回调接口判断异常！");
                }
            }
        } catch (Exception e) {
            returnXml = "<xml><return_code><![CDATA[FAIL]]></return_code> <return_msg><![CDATA[参数校验错误]]></return_msg></xml>";
            log.error("支付回调函数判断异常结果->{}",errCode);
        }
        log.info("微信支付回调判断结束->{}"+returnXml);
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(response.getOutputStream());
            out.write(returnXml.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("支付回调函数结果返回异常->{}",errCode);
        }
    }
}
