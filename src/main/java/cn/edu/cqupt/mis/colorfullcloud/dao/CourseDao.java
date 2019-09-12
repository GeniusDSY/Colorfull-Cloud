package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/1 9:38
 * @desc : 课程数据库接口
 */
@Repository
public interface CourseDao {

    /**
     * 默认查询所有课程
     * @return 返回所有课程
     */
    List<CourseEntity> selectAllCourses();

    /**
     * 查询某订单某机构下的所有课程
     * @param courseIds
     * @return
     */
    List<CourseEntity> selectOrderCoursesByIds(List<Integer> courseIds);

    /**
     * 按机构查询所有课程
     * @param id 机构id
     * @return 返回某机构所有课程
     */
    List<CourseEntity> selectCoursesByInstitutionId(Integer id);

    /**
     * 按类别查询所有课程
     * @param id 类别id
     * @return 某类别下的所有课程
     */
    List<CourseEntity> selectCoursesByCategoryId(Integer id);

}