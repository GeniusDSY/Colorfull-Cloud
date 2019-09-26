package cn.edu.cqupt.mis.colorfullcloud.service;

import cn.edu.cqupt.mis.colorfullcloud.domain.dto.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.SuggestionEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.ActivityVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/26 21:04
 * @desc :
 */
public interface BackManageService {

    /**
     * 查询所有意见反馈
     * @return
     */
    List<SuggestionEntity> getAllSuggestions();

    /**
     * 创建新机构
     * @param institutionDto
     * @return
     */
    List<InstitutionVo> createInstitution(InstitutionDto institutionDto);

    /**
     * 删除机构信息
     * @param institutionIdList
     * @return
     */
    List<InstitutionVo> deleteInstitution(List<Integer> institutionIdList);

    /**
     * 修改机构信息
     * @param institutionDto
     * @return
     */
    List<InstitutionVo> modifyInstitution(InstitutionDto institutionDto);

    /**
     * 查询所有机构信息
     * @return
     */
    List<InstitutionVo> allInstitutions();

    /**
     * 创建新课程
     * @param courseDto
     * @return
     */
    List<CourseVo> createCourse(CourseDto courseDto);

    /**
     * 删除课程信息
     * @param courseIdList
     * @return
     */
    List<CourseVo> deleteCourse(List<Integer> courseIdList);

    /**
     * 修改课程信息
     * @param courseDto
     * @return
     */
    List<CourseVo> modifyCourse(CourseDto courseDto);

    /**
     * 查询某机构的所有课程信息
     * @param institutionId
     * @return
     */
    List<CourseVo> allCourses(Integer institutionId);

    /**
     * 创建新的类别
     * @param categoryDto
     * @return
     */
    List<CategoryVo> createCategory(CategoryDto categoryDto);

    /**
     * 删除类别
     * @param categoryIdList
     * @return
     */
    List<CategoryVo> deleteCategory(List<Integer> categoryIdList);

    /**
     * 修改类别信息
     * @param categoryDto
     * @return
     */
    List<CategoryVo> modifyCategory(CategoryDto categoryDto);

    /**
     * 查询所有类别
     * @return
     */
    List<CategoryVo> allCategories();

    /**
     * 创建新教师
     * @param teacherDto
     * @return
     */
    List<TeacherEntity> createTeacher(TeacherDto teacherDto);

    /**
     * 删除教师信息
     * @param teacherIdList
     * @return
     */
    List<TeacherEntity> deleteTeacher(List<Integer> teacherIdList);

    /**
     * 修改教师信息
     * @param teacherDto
     * @return
     */
    List<TeacherEntity> modifyTeacher(TeacherDto teacherDto);

    /**
     * 查询所有教师信息
     * @return
     */
    List<TeacherEntity> allTeachers();

    /**
     * 创建新的活动
     * @param activityDto
     * @return
     */
    List<ActivityVo> createActivity(ActivityDto activityDto);

    /**
     * 删除活动信息
     * @param activityIdList
     * @return
     */
    List<ActivityVo> deleteActivity(List<Integer> activityIdList);

    /**
     * 修改活动信息
     * @param activityDto
     * @return
     */
    List<ActivityVo> modifyActivity(ActivityDto activityDto);

    /**
     * 查询所有活动信息
     * @return
     */
    List<ActivityVo> allActivities();

}
