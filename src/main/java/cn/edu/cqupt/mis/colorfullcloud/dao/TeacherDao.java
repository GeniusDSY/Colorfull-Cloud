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
     * 插入新的教师信息
     * @param teacherEntity 需插入教师
     * @return true/false
     */
    Boolean insertTeacher(TeacherEntity teacherEntity);

    /**
     * 删除教师列表
     * @param teacherIdList 需删除的教师id列表
     * @return true/false
     */
    Boolean deleteTeacherByIdList(List<Integer> teacherIdList);

    /**
     * 查询所有教师
     * @return
     */
    List<TeacherEntity> selectAllTeachers();

    /**
     * 修改教师信息
     * @param transformOne
     * @return
     */
    Boolean updateTeacher(TeacherEntity transformOne);
}
