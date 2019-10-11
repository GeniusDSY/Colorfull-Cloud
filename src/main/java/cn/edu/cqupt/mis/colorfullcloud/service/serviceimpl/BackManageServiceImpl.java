package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.UploadException;
import cn.edu.cqupt.mis.colorfullcloud.dao.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.ActivityVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.service.BackManageService;
import cn.edu.cqupt.mis.colorfullcloud.util.ServiceUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.TransformUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.UploadFactory;
import cn.edu.cqupt.mis.colorfullcloud.util.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/26 21:05
 * @desc :
 */
@Service
public class BackManageServiceImpl implements BackManageService {

    //引入第一步的七牛配置
    @Value("${qiniu.access.key}")
    private String accesskey;

    @Value("${qiniu.secret.key}")
    private String secretKey;

    @Value("${qiniu.bucket.name}")
    private String bucketName;

    @Value("${qiniu.bucket.host.name}")
    private String bucketHostName;

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
        InstitutionEntity institutionEntity = new InstitutionEntity();
        TransformUtil.transformOne(institutionDto,institutionEntity);
        ServiceUtil.checkSqlExecuted(institutionDao.insertInstitution(institutionEntity));
        return allInstitutions();
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
        List<InstitutionVo> institutionVoList = new ArrayList<>();
        TransformUtil.transformList(institutionDao.selectAllInstitutions(),institutionVoList,InstitutionVo.class);
        return institutionVoList;
    }

    /**
     * 创建新课程
     * @param courseDto
     * @return
     */
    @Override
    public List<CourseVo> createCourse(CourseDto courseDto) {
        CourseEntity courseEntity = new CourseEntity();
        TransformUtil.transformOne(courseDto,courseEntity);
        ServiceUtil.checkSqlExecuted(courseDao.insertCourse(courseEntity));
        return allCourses(courseDto.getInstitutionId());
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
        List<CourseVo> courseVoList = new ArrayList<>();
        TransformUtil.transformList(courseDao.selectCoursesByInstitutionId(institutionId),courseVoList,CourseVo.class);
        return courseVoList;
    }

    /**
     * 创建新的类别
     * @param categoryDto
     * @return
     */
    @Override
    public List<CategoryVo> createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        TransformUtil.transformOne(categoryDto,categoryEntity);
        ServiceUtil.checkSqlExecuted(categoryDao.insertCategory(categoryEntity.getType()));
        return allCategories();
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
        List<CategoryVo> categoryVoList = new ArrayList<>();
        TransformUtil.transformList(categoryDao.selectAllCategories(),categoryVoList,CategoryVo.class);
        return categoryVoList;
    }

    /**
     * 创建新教师
     * @param teacherDto
     * @return
     */
    @Override
    public List<TeacherEntity> createTeacher(TeacherDto teacherDto) {
        TeacherEntity teacherEntity = new TeacherEntity();
        TransformUtil.transformOne(teacherDto,teacherEntity);
        List<TeacherEntity> teacherEntityList = new ArrayList<>();
        ServiceUtil.checkSqlExecuted(teacherDao.insertTeacher(teacherEntityList));
        return allTeachers();
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
        return teacherDao.selectAllTeachers();
    }

    /**
     * 创建新的活动
     *
     * @param activityDto
     * @return
     */
    @Override
    public List<ActivityVo> createActivity(ActivityDto activityDto) {
        ActivityEntity activityEntity = new ActivityEntity();
        TransformUtil.transformOne(activityDto,activityEntity);
        ServiceUtil.checkSqlExecuted(activityDao.insertActivity(activityEntity));
        return allActivities();
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
        List<ActivityVo> activityVoList = new ArrayList<>();
        TransformUtil.transformList(activityDao.selectAllActivities(),activityVoList,ActivityVo.class);
        return activityVoList;
    }

    /**
     * 上传图片
     * @param fileType
     * @param multipartFile
     * @return
     */
    @Override
    public String updateImages(Integer fileType,Integer institutionId,String name,Integer courseId, MultipartFile multipartFile) throws UploadException {
        String filePath = "";
        if (fileType == 1){
            filePath = Status.INSTITUTION_ICON;
            String url = updateImage(filePath,multipartFile);
            ServiceUtil.checkSqlExecuted(institutionDao.updateInstitutionIcon(institutionId,url));
            return url;
        }else if (fileType == 2){
            filePath = Status.INSTITUTION_PICTURE;
            String url = updateImage(filePath,multipartFile);
            ServiceUtil.checkSqlExecuted(institutionDao.updateInstitutionPicture(institutionId,name,url));
            return url;
        }else if (fileType == 3){
            filePath = Status.COURSE_ICON;
            String url = updateImage(filePath,multipartFile);
            ServiceUtil.checkSqlExecuted(courseDao.updateCourseIcon(courseId,url));
            return url;
        }else {
            throw new ServerException("图片类型不正确");
        }
    }

    private String updateImage(String filePath, MultipartFile multipartFile) throws UploadException {
        UploadUtil uploadUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        return uploadUtil.uploadFile(filePath, multipartFile);
    }

}
