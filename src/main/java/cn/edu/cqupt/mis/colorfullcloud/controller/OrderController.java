package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.OrderDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.OrderVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 23:27
 * @desc : 订单模块controller
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @GetMapping("allOrders")
    public ResponseEntity<OrderVo> allOrders(Integer userId){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @PostMapping("createOrder")
    public ResponseEntity<OrderVo> createOrder(OrderDto orderDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

}
