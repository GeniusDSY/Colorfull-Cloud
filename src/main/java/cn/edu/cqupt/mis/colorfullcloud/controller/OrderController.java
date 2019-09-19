package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.OrderDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.OrderVo;
import cn.edu.cqupt.mis.colorfullcloud.service.OrderService;
import cn.edu.cqupt.mis.colorfullcloud.util.CacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    private OrderService orderService;

    @ApiOperation("获得该用户的所有订单")
    @GetMapping("allOrders")
    public ResponseEntity<List<OrderVo>> allOrders(Integer userId){
        List<OrderVo> orderVoList = orderService.allUserOrders(userId);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,orderVoList);
    }

    @ApiOperation("创建订单")
    @PostMapping("createOrder")
    public ResponseEntity<List<OrderVo>> createOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,orderService.createOrder(orderDto));
    }

    @ApiOperation("删除订单列表")
    @DeleteMapping("deleteOrders")
    public ResponseEntity<List<OrderVo>> deleteOrders(@RequestParam Integer userId,@RequestBody List<String> orderIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,orderService.deleteOrders(userId,orderIdList));
    }

    @ApiOperation("取消订单")
    @PostMapping("cancelOrder")
    public ResponseEntity<List<OrderVo>> cancelOrder(Integer userId,String orderId){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,orderService.cancelOrder(userId,orderId));
    }

}
