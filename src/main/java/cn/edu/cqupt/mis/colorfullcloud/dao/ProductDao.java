package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ProductEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/5 14:27
 * @desc : 订单产品数据库接口
 */
@Repository
public interface ProductDao {

    /**
     * 通过订单id查询订单详情
     * @param orderId
     * @return 返回订单详情中的所有产品集合
     */
    List<ProductEntity> selectProductByOrderId(String orderId);

    /**
     * 通过OrderId查询所含机构
     * @param orderId
     * @return 返回订单中的所有机构id
     */
    List<Integer> selectInstitutionIdsByOrderId(String orderId);

    /**
     * 根据订单id和机构id查询包含所有课程id
     * @param orderId
     * @param institutionId
     * @return 返回所有课程id
     */
    List<Integer> selectCourseIdsByOrderIdAndInstitutionId(@Param("orderId")String orderId,@Param("institutionId")Integer institutionId);

    /**
     * 查询订单课程数量
     * @param orderId
     * @param courseId
     * @return
     */
    Integer selectCountByOrderIdAndCourseId(@Param("orderId") String orderId,@Param("courseId")Integer courseId);


    /**
     * 插入所有
     * @param productEntityList
     * @return
     */
    Boolean insertProducts(List<ProductEntity> productEntityList);

}
