package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.SuggestionEntity;
import cn.edu.cqupt.mis.colorfullcloud.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/10/21 20:45
 * @desc : 支付Controller
 */
@Api(tags = "支付模块")
@RestController
@RequestMapping("pay")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("prePay")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int"),
            @ApiImplicitParam(name = "orderId",value = "订单id",dataType = "string"),
            @ApiImplicitParam(name = "totalFee",value = "金额（单位为分）",dataType = "int"),
            @ApiImplicitParam(name = "SPbillCreateIp",value = "终端ip",dataType = "string")
    })
    public ResponseEntity prePay(Integer userId,String orderId,Integer totalFee,String SPbillCreateIp){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,payService.prePayResult(userId,orderId,totalFee,SPbillCreateIp));
    }

    @PostMapping("updatePayStatus")
    public ResponseEntity updatePayStatus(String orderId,String wechatOrderId){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,payService.updateOrderStatus(orderId,wechatOrderId));
    }

}
