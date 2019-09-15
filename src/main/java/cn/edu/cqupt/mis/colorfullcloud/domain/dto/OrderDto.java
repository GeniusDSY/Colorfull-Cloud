package cn.edu.cqupt.mis.colorfullcloud.domain.dto;

import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 23:45
 * @desc : 订单传输实体类
 */
@ApiModel
@Data
@ToString
public class OrderDto {

    @ApiModelProperty(name ="userId",value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "total",value = "价格总额")
    private Float total;
    @ApiModelProperty(name="discount",value = "折扣总额")
    private Float discount;
    @ApiModelProperty(name = "amount",value = "实际支付金额")
    private Float amount;
    @ApiModelProperty(name = "productDtoList",value = "订单详情列表")
    private List<ProductDto> productDtoList;

}
