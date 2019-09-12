package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 18:09
 * @desc : 订单数据库接口
 */
@Repository
public interface OrderDao {

    /**
     * 查询某用户的订单
     * @param userId 用户Id
     * @return 该用户的所有订单
     */
    List<OrderEntity> selectAllOrdersByUserId(Integer userId);

}
