package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ThirdPartyServiceException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.UploadException;
import cn.edu.cqupt.mis.colorfullcloud.dao.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.TencentMapResult;
import cn.edu.cqupt.mis.colorfullcloud.service.BackManageService;
import cn.edu.cqupt.mis.colorfullcloud.util.*;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class BackManageServiceImpl implements BackManageService {

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
    private ProductDao productDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private PictureDao pictureDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private ActivityDao activityDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private ChildrenDao childrenDao;
    @Resource
    private DistanceUtil distanceUtil;
    /**
     * 查询所有意见反馈
     * @return
     */
    @Override
    public List<SuggestionEntity> getAllSuggestions() {
        List<SuggestionEntity> suggestionEntityList= userDao.selectAllSuggestions();
        suggestionEntityList.forEach(suggestion -> {
            suggestion.setNickName(userDao.selectUserById(suggestion.getUserId()).getNickName());
            suggestion.setPhone(userDao.selectUserById(suggestion.getUserId()).getPhone());
        });
        return suggestionEntityList;
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
            //获取当前地址的经纬度进行赋值存储
            obtainLocationAndAssignment(institutionEntity);
            ServiceUtil.checkSqlExecuted(institutionDao.insertInstitution(institutionEntity));
            return allInstitutions();
    }

    private void obtainLocationAndAssignment(InstitutionEntity institutionEntity) {
        try {
            TencentMapResult result = distanceUtil.getCoordinate(institutionEntity.getAddress());
            institutionEntity.setLat(result.getResult().getLocation().getLat());
            institutionEntity.setLng(result.getResult().getLocation().getLng());
        }catch (Exception e){
            throw new ThirdPartyServiceException("请检查地址是否输入正确！");
        }
    }

    /**
     * 删除机构信息
     * @param institutionIdList
     * @return
     */
    @Override
    public List<InstitutionVo> deleteInstitution(List<Integer> institutionIdList) {
        //查询所有图片信息
        List<String> imageList = new ArrayList<>();
        imageList.addAll(institutionDao.selectIconByInstitutionId(institutionIdList));
        imageList.addAll(pictureDao.selectPictureByInstitutionIdList(institutionIdList));
        ServiceUtil.checkSqlExecuted(institutionDao.deleteInstitutionByIds(institutionIdList));
        if (imageList.size() > 1){
            imageList.forEach(this::fileDelete);
        }
        return allInstitutions();
    }

    /**
     * 修改机构信息
     * @param institutionDto
     * @return
     */
    @Override
    public List<InstitutionVo> modifyInstitution(InstitutionDto institutionDto) {
        ServiceUtil.checkSqlExecuted(institutionDao.updateInstitution((InstitutionEntity) TransformUtil.transformOne(institutionDto,new InstitutionEntity())));
        return allInstitutions();
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
        try {
            CourseEntity courseEntity = new CourseEntity();
            TransformUtil.transformOne(courseDto,courseEntity);
            ServiceUtil.checkSqlExecuted(courseDao.insertCourse(courseEntity));
            return allCourses(courseDto.getInstitutionId());
        }catch (Exception e){
            log.error("BackManageService -> createCourse() -> {}",e);
            throw new ServerException("创建课程失败！");
        }
    }

    /**
     * 创建活动课程
     * @param courseDto
     * @return
     */
    @Override
    public List<CourseVo> createActivityCourse(CourseDto courseDto) {
        try {
            CourseEntity courseEntity = new CourseEntity();
            TransformUtil.transformOne(courseDto,courseEntity);
            courseEntity.setStatus(1);
            ServiceUtil.checkSqlExecuted(courseDao.insertCourse(courseEntity));
            return allCourses(courseDto.getInstitutionId());
        }catch (Exception e){
            log.error("BackManageService -> createActivityCourse() -> {}",e);
            throw new ServerException("创建活动课程失败！");
        }
    }

    /**
     * 删除课程信息
     * @param institutionId 机构id
     * @param courseIdList
     * @return
     */
    @Override
    public List<CourseVo> deleteCourse(Integer institutionId,List<Integer> courseIdList) {
        List<String> imageList = courseDao.selectImageByCourseId(courseIdList);
        ServiceUtil.checkSqlExecuted(courseDao.deleteCourseByIds(courseIdList));
        if (imageList.size() > 1){
            imageList.forEach(this::fileDelete);
        }
        return allCourses(institutionId);
    }

    /**
     * 修改课程信息
     * @param courseDto
     * @return
     */
    @Override
    public List<CourseVo> modifyCourse(CourseDto courseDto) {
        ServiceUtil.checkSqlExecuted(courseDao.updateCourse((CourseEntity)TransformUtil.transformOne(courseDto,new CourseEntity())));
        return allCourses(courseDto.getInstitutionId());
    }

    /**
     * 查询某机构的所有课程信息
     * @param institutionId
     * @return
     */
    @Override
    public List<CourseVo> allCourses(Integer institutionId) {
        List<CourseVo> courseVoList = new ArrayList<>();
        List<CourseEntity> courseEntityList = courseDao.selectCoursesByInstitutionId(institutionId);
        courseEntityList.forEach(courseEntity -> courseEntity.setTeacherIntroduction(teacherDao.selectTeacherById(courseEntity.getTeacherId())));
        TransformUtil.transformList(courseEntityList,courseVoList,CourseVo.class);
        return courseVoList;
    }

    /**
     * 创建新的类别
     * @param categoryDto
     * @return
     */
    @Override
    public List<CategoryVo> createCategory(CategoryDto categoryDto) {
        try {
            CategoryEntity categoryEntity = new CategoryEntity();
            TransformUtil.transformOne(categoryDto,categoryEntity);
            ServiceUtil.checkSqlExecuted(categoryDao.insertCategory(categoryEntity.getType()));
            return allCategories();
        }catch (Exception e){
            log.error("BackManageService -> createCategory() -> {}",e);
            throw new ServerException("创建分类失败！");
        }
    }

    /**
     * 删除类别
     * @param categoryIdList
     * @return
     */
    @Override
    public List<CategoryVo> deleteCategory(List<Integer> categoryIdList) {
        ServiceUtil.checkSqlExecuted(categoryDao.deleteCategories(categoryIdList));
        return allCategories();
    }

    /**
     * 修改类别信息
     * @param categoryDto
     * @return
     */
    @Override
    public List<CategoryVo> modifyCategory(CategoryDto categoryDto) {
        ServiceUtil.checkSqlExecuted(categoryDao.updateCategory((CategoryEntity)TransformUtil.transformOne(categoryDto,new CategoryEntity())));
        return allCategories();
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
        try {
            TeacherEntity teacherEntity = new TeacherEntity();
            TransformUtil.transformOne(teacherDto,teacherEntity);
            ServiceUtil.checkSqlExecuted(teacherDao.insertTeacher(teacherEntity));
            return allTeachers();
        }catch (Exception e){
            log.error("BackManageService -> createTeacher() -> {}",e);
            throw new ServerException("创建教师失败！");
        }
    }

    /**
     * 删除教师信息
     *
     * @param teacherIdList
     * @return
     */
    @Override
    public List<TeacherEntity> deleteTeacher(List<Integer> teacherIdList) {
        ServiceUtil.checkSqlExecuted(teacherDao.deleteTeacherByIdList(teacherIdList));
        return allTeachers();
    }

    /**
     * 修改教师信息
     *
     * @param teacherDto
     * @return
     */
    @Override
    public List<TeacherEntity> modifyTeacher(TeacherDto teacherDto) {
        ServiceUtil.checkSqlExecuted(teacherDao.updateTeacher((TeacherEntity)TransformUtil.transformOne(teacherDto,new TeacherEntity())));
        return allTeachers();
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
        try {
            ActivityEntity activityEntity = new ActivityEntity();
            TransformUtil.transformOne(activityDto,activityEntity);
            ServiceUtil.checkSqlExecuted(activityDao.insertActivity(activityEntity));
            return allActivities();
        }catch (Exception e){
            log.error("BackManageService -> createActivity() -> {}",e);
            throw new ServerException("创建活动失败！");
        }
    }

    /**
     * 删除活动信息
     *
     * @param activityIdList
     * @return
     */
    @Override
    public List<ActivityVo> deleteActivity(List<Integer> activityIdList) {
        ServiceUtil.checkSqlExecuted(activityDao.deleteActivities(activityIdList));
        return allActivities();
    }

    /**
     * 修改活动信息
     *
     * @param activityDto
     * @return
     */
    @Override
    public List<ActivityVo> modifyActivity(ActivityDto activityDto) {
        ServiceUtil.checkSqlExecuted(activityDao.updateActivity((ActivityEntity)TransformUtil.transformOne(activityDto,new ActivityEntity())));
        return allActivities();
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
        try {
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
        }catch (Exception e){
            log.error("BackManageService -> updateImages() -> {}",e);
            throw new ServerException("更新图片失败！");
    }
    }

    /**
     * 获取所有已购课程信息
     * @return
     */
    @Override
    public List<BackCoursesVo> getAllCoursesInOrders() {
        List<BackCoursesVo> backCoursesVoList = new ArrayList<>();
        List<ProductEntity> productEntityList = productDao.selectAllProducts();
        productEntityList.forEach(
                product -> {
                    BackCoursesVo backCoursesVo = new BackCoursesVo();
                    OrderEntity order = orderDao.selectOrderId(product.getOrderId());
                    if (order.getStatus() != 1){
                        return;
                    }else {
                        backCoursesVo.setBuyerName(userDao.selectUserById(order.getUserId()).getNickName());
                        backCoursesVo.setChildren(childrenDao.selectAllChildrenByCard(order.getChildrenCard()));
                        backCoursesVo.setCourseName(courseDao.selectCourseById(product.getCourseId()).getCourseName());
                        backCoursesVo.setInstitutionName(institutionDao.selectInstitutionById(product.getInstitutionId()).getInstitutionName());
                        backCoursesVo.setCount(product.getCount());
                        backCoursesVo.setCycleNum(product.getCycleSum());
                        backCoursesVo.setIsActivity(order.getActivityId());
                    }
                    backCoursesVoList.add(backCoursesVo);
                }
        );
        return backCoursesVoList;
    }

    private String updateImage(String filePath, MultipartFile multipartFile) throws UploadException {
        UploadUtil uploadUtil = UploadFactory.createUpload(this.accesskey, this.secretKey,
                this.bucketHostName, this.bucketName);
        return uploadUtil.uploadFile(filePath, multipartFile);
    }



    /**
     * 单文件删除
     * @param key 键值
     * @return
     */
    private void fileDelete(String key) {
        Auth auth = Auth.create(this.accesskey, this.secretKey);
        BucketManager bucketManager = new BucketManager(auth);
        //指定文件所在的存储空间和需要删除的文件
        try {
            if ("http://picture.geniusdsy.cn/picture/20191102/6JgFngflQRVj.png?imageslim".equals(key)
                    ||"http://picture.geniusdsy.cn/picture/20191102/b76V6ofEsuOz.png".equals(key)){
                return;
            }else {
                bucketManager.delete(this.bucketName, key);
            }
        } catch (QiniuException e) {
            throw new ServerException("文件已删除或文件不存在！");
        }
    }


}
