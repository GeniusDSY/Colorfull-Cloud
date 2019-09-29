package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ActivityChildrenEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/24 21:10
 * @desc :
 */
@Repository
public interface ActivityChildrenDao {


    /**
     * 查询是否为某孩子购买过活动项
     * @param activityId 活动id
     * @param childrenCard 孩子身份证号
     * @return
     */
    List<ActivityChildrenEntity> selectActivityChildrenByActivityIdAndChildrenCard(@Param("activityId")Integer activityId, @Param("childrenCard")String childrenCard);

    /**
     * 插入活动订单与孩子的对应记录
     * @param activityChildrenEntity
     * @return
     */
    Boolean insertActivityChildren(ActivityChildrenEntity activityChildrenEntity);


}
