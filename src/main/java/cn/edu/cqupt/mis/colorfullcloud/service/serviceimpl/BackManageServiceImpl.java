package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.dao.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.SuggestionEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.ActivityVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.service.BackManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/26 21:05
 * @desc :
 */
@Service
public class BackManageServiceImpl implements BackManageService {

    @Resource
    private UserDao userDao;
    @Resource
    private InstitutionDao institutionDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private ActivityDao activityDao;
    /**
     * 查询所有意见反馈
     * @return
     */
    @Override
    public List<SuggestionEntity> getAllSuggestions() {
        return userDao.selectAllSuggestions();
    }

    /**
     * 创建新机构
     * @param institutionDto
     * @return
     */
    @Override
    public List<InstitutionVo> createInstitution(InstitutionDto institutionDto) {
        return null;
    }

    /**
     * 删除机构信息
     * @param institutionIdList
     * @return
     */
    @Override
    public List<InstitutionVo> deleteInstitution(List<Integer> institutionIdList) {
        return null;
    }

    /**
     * 修改机构信息
     * @param institutionDto
     * @return
     */
    @Override
    public List<InstitutionVo> modifyInstitution(InstitutionDto institutionDto) {
        return null;
    }

    /**
     * 查询所有机构信息
     * @return
     */
    @Override
    public List<InstitutionVo> allInstitutions() {
        return null;
    }

    /**
     * 创建新课程
     * @param courseDto
     * @return
     */
    @Override
    public List<CourseVo> createCourse(CourseDto courseDto) {
        return null;
    }

    /**
     * 删除课程信息
     * @param courseIdList
     * @return
     */
    @Override
    public List<CourseVo> deleteCourse(List<Integer> courseIdList) {
        return null;
    }

    /**
     * 修改课程信息
     * @param courseDto
     * @return
     */
    @Override
    public List<CourseVo> modifyCourse(CourseDto courseDto) {
        return null;
    }

    /**
     * 查询某机构的所有课程信息
     * @param institutionId
     * @return
     */
    @Override
    public List<CourseVo> allCourses(Integer institutionId) {
        return null;
    }

    /**
     * 创建新的类别
     * @param categoryDto
     * @return
     */
    @Override
    public List<CategoryVo> createCategory(CategoryDto categoryDto) {
        return null;
    }

    /**
     * 删除类别
     * @param categoryIdList
     * @return
     */
    @Override
    public List<CategoryVo> deleteCategory(List<Integer> categoryIdList) {
        return null;
    }

    /**
     * 修改类别信息
     * @param categoryDto
     * @return
     */
    @Override
    public List<CategoryVo> modifyCategory(CategoryDto categoryDto) {
        return null;
    }

    /**
     * 查询所有类别
     * @return
     */
    @Override
    public List<CategoryVo> allCategories() {
        return null;
    }

    /**
     * 创建新教师
     * @param teacherDto
     * @return
     */
    @Override
    public List<TeacherEntity> createTeacher(TeacherDto teacherDto) {
        return null;
    }

    /**
     * 删除教师信息
     *
     * @param teacherIdList
     * @return
     */
    @Override
    public List<TeacherEntity> deleteTeacher(List<Integer> teacherIdList) {
        return null;
    }

    /**
     * 修改教师信息
     *
     * @param teacherDto
     * @return
     */
    @Override
    public List<TeacherEntity> modifyTeacher(TeacherDto teacherDto) {
        return null;
    }

    /**
     * 查询所有教师信息
     *
     * @return
     */
    @Override
    public List<TeacherEntity> allTeachers() {
        return null;
    }

    /**
     * 创建新的活动
     *
     * @param activityDto
     * @return
     */
    @Override
    public List<ActivityVo> createActivity(ActivityDto activityDto) {
        return null;
    }

    /**
     * 删除活动信息
     *
     * @param activityIdList
     * @return
     */
    @Override
    public List<ActivityVo> deleteActivity(List<Integer> activityIdList) {
        return null;
    }

    /**
     * 修改活动信息
     *
     * @param activityDto
     * @return
     */
    @Override
    public List<ActivityVo> modifyActivity(ActivityDto activityDto) {
        return null;
    }

    /**
     * 查询所有活动信息
     *
     * @return
     */
    @Override
    public List<ActivityVo> allActivities() {
        return null;
    }
}
