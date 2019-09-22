package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/22 16:28
 * @desc :教师数据库接口
 */
@Repository
public interface TeacherDao {

    /**
     * 根据id查询教师信息
     * @param teacherId
     * @return 教师信息
     */
    TeacherEntity selectTeacherById(Integer teacherId);

    /**
     * 插入新的教师信息List
     * @param teacherList 需插入教师列表
     * @return true/false
     */
    Boolean insertTeacher(List<TeacherEntity> teacherList);

    /**
     * 删除教师列表
     * @param teacherIdList 需删除的教师id列表
     * @return true/false
     */
    Boolean deleteTeacherByIdList(List<Integer> teacherIdList);

}
