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

}
