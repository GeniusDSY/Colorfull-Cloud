package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.dao.OrderDao;
import cn.edu.cqupt.mis.colorfullcloud.service.PayService;
import cn.edu.cqupt.mis.colorfullcloud.util.ServiceUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.XmlBeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author :DengSiYuan
 * @date :2019/10/22 15:19
 * @desc :
 */
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
            System.out.println(key + " : " + value);
        }
        return map;
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param wechatOrderId
     * @return
     */
    @Override
    public Boolean updateOrderStatus(String orderId, String wechatOrderId) {
        if (xmlBeanUtils.getPayStatus(wechatOrderId)){
            ServiceUtil.checkSqlExecuted(orderDao.modifyOrderSuccessByOrderId(orderId,wechatOrderId));
            return true;
        }else {
            throw new ServerException("支付失败！请重新发起支付！");
        }
    }
}
