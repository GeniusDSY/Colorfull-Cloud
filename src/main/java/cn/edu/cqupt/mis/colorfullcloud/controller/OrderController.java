package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.OrderDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.OrderVo;
import cn.edu.cqupt.mis.colorfullcloud.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 23:27
 * @desc : 订单模块controller
 */
@Api(tags = "订单模块")
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("(已测)获得该用户的所有订单")
    @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int")
    @GetMapping("allOrders")
    public ResponseEntity<List<OrderVo>> allOrders(Integer userId){
        List<OrderVo> orderVoList = orderService.allUserOrders(userId);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,orderVoList);
    }

    @ApiOperation("(已测)创建订单(包括活动订单)")
    @ApiImplicitParam(name = "childrenCard",value = "孩子身份证号",dataType = "string",allowEmptyValue = true)
    @PostMapping("createOrder")
    public ResponseEntity<List<OrderVo>> createOrder(@RequestParam String childrenCard,@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,orderService.createOrder(childrenCard,orderDto));
    }

    @ApiOperation("(已测)获取目前剩余可买的活动课时")
    @GetMapping("remainTime")
    public ResponseEntity<Integer> remainTime(@RequestParam String childrenCard,@RequestParam Integer activityId){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,orderService.inquiryRemainTime(childrenCard,activityId));
    }

    @ApiOperation("(已测)删除订单列表(支持批量删除，均传list无法还原)")
    @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int")
    @DeleteMapping("deleteOrders")
    public ResponseEntity<List<OrderVo>> deleteOrders(@RequestParam Integer userId,@RequestBody List<String> orderIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,orderService.deleteOrders(userId,orderIdList));
    }

    @ApiOperation("(已测)取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int"),
            @ApiImplicitParam(name = "orderId",value = "订单id",dataType = "string")
    })
    @PostMapping("cancelOrder")
    public ResponseEntity<List<OrderVo>> cancelOrder(Integer userId,String orderId){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,orderService.cancelOrder(userId,orderId));
    }

    @ApiOperation("更新订单支付状态")
    @PostMapping("updatePayStatus")
    public ResponseEntity updatePayStatus(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,null);
    }
}
