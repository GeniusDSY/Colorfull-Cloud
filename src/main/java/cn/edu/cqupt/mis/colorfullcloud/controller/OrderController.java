package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.OrderDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.OrderVo;
import cn.edu.cqupt.mis.colorfullcloud.service.OrderService;
import cn.edu.cqupt.mis.colorfullcloud.util.CacheUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 23:27
 * @desc : 订单模块controller
 */
@Api("订单模块")
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private CacheUtil cacheUtil;
    @Resource
    private OrderService orderService;

    @GetMapping("allOrders")
    public ResponseEntity<List<OrderVo>> allOrders(HttpServletRequest request){
        Integer userId = Integer.valueOf(cacheUtil.getCookie(request));
        List<OrderVo> orderVoList = orderService.allUserOrders(userId);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,orderVoList);
    }

    @PostMapping("createOrder")
    public ResponseEntity<OrderVo> createOrder(OrderDto orderDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

}
