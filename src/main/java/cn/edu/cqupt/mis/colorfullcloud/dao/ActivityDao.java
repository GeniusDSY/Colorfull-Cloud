package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ActivityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 21:49
 * @desc :
 */
@Repository
public interface ActivityDao {

    /**
     * 查询所有活动信息
     * @return 所有活动列表
     */
    List<ActivityEntity> selectAllActivities();

    /**
     * 查询某个活动详情
     * @param activityId
     * @return
     */
    ActivityEntity selectActivityById(Integer activityId);

    /**
     * 插入新活动
     * @param activityEntity
     * @return
     */
    Boolean insertActivity(ActivityEntity activityEntity);

}
