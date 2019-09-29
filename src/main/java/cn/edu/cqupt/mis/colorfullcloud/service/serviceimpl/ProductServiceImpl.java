package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ParameterException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ThirdPartyServiceException;
import cn.edu.cqupt.mis.colorfullcloud.dao.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ActivityEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.CategoryEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.CourseEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.InstitutionEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.Location;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.TencentMapResult;
import cn.edu.cqupt.mis.colorfullcloud.service.ProductService;
import cn.edu.cqupt.mis.colorfullcloud.util.DistanceUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.TransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :DengSiYuan
 * @date :2019/9/1 8:50
 * @desc : 产品服务实现类
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private InstitutionDao institutionDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private PictureDao pictureDao;
    @Resource
    private ActivityDao activityDao;
    @Resource
    private DistanceUtil distanceUtil;

    /**
     * 默认方法获取所有产品
     * @return 产品集合
     */
    @Override
    public List<InstitutionVo> allDefaultProducts(){
        try {
            List<InstitutionVo> institutionVoList = getAllInstitutions();
            return setCourseVoList(institutionVoList);
        }catch (Exception e){
            log.error("ProductService -> allDefaultProducts()->{}",e.getMessage());
            throw new ServerException("获取产品列表异常");
        }

    }

    /**
     * 按照距离顺序排序所有产品
     * @return 所有机构含产品按距离排序的集合
     */
    @Override
    public List<InstitutionVo> allDistantProducts() {
        try {
            //查询数据库机构
            List<InstitutionEntity> institutionEntityList = institutionDao.selectAllInstitutions();
            /**
             * 1、参数拼接
             * 2、发起Http请求
             * 3、处理结果
             * */
            return getDistanceResult(institutionEntityList).stream().sorted(Comparator.comparing((InstitutionVo::getDistance))).collect(Collectors.toList());
        }catch (Exception e){
            log.error("ProductServiceImpl -> allDistantProduct()：{}",e);
            throw new ServerException("按距离远近查询课程出现异常！");
        }

    }

    /**
     * 分类查询所有产品
     * @return 按类分组返回
     */
    @Override
    public List<CategoryVo> allCategoryProducts() {
        try {
            List<CategoryEntity> categoryEntityList = categoryDao.selectAllCategories();
            List<CategoryVo> categoryVoList = new ArrayList<>();
            TransformUtil.transformList(categoryEntityList,categoryVoList,CategoryVo.class);
            for (CategoryVo categoryVo : categoryVoList) {
                categoryVo.setCourseVoList(getAllCategoryCourses(categoryVo.getCategoryId()));
            }
            return categoryVoList;
        }catch (Exception e){
            log.error("ProductService -> allCategoryProducts()->{}",e);
            throw new ServerException("分类获取课程出现异常");
        }
    }


    /**
     * 获取所有机构
     * @return 所有机构含产品的集合
     */
    private List<InstitutionVo> getAllInstitutions(){
        try {
            List<InstitutionVo> institutionVoList = new ArrayList<>();
            TransformUtil.transformList(institutionDao.selectAllInstitutions(),institutionVoList,InstitutionVo.class);
            return institutionVoList;
        }catch (Exception e){
            log.error("ProductService -> getAllInstitutions()->{}",e.getMessage());
            throw new ServerException("获取所有机构->数据库出现异常");
        }
    }

    @Override
    public List<CourseVo> allCourses(){
        try {
            List<CourseVo> courseVoList = new ArrayList<>();
            List<CourseEntity> courseEntityList = courseDao.selectAllCourses();
            courseEntityList.forEach(courseEntity ->
                    courseEntity.setTeacherIntroduction(teacherDao.selectTeacherById(courseEntity.getTeacherId())));
            TransformUtil.transformList(courseEntityList,courseVoList,CourseVo.class);
            return courseVoList;
        }catch (Exception e){
            log.error("ProductService -> getAllCourses()->{}",e.getMessage());
            throw new ServerException("获取所有课程->数据库出现异常");
        }
    }

    /**
     * 获取机构所有介绍图片
     * @param institutionId
     * @return 所有图片链接集合
     */
    @Override
    public InstitutionVo allInstitutionPictures(Integer institutionId) {
        try {
            InstitutionVo institutionVo = new InstitutionVo();
            TransformUtil.transformOne(institutionDao.selectInstitutionById(institutionId),institutionVo);
            institutionVo.setPictures(pictureDao.selectInstitutionPicture(institutionId));
            return institutionVo;
        }catch (Exception e){
            log.error("ProductService -> allInstitutionPictures()->{}",e);
            throw new ServerException("获取机构信息、图片->数据库出现异常");
        }
    }

    /**
     * 获取所有活动信息
     * @return 所有活动信息
     */
    @Override
    public List<ActivityEntity> allActivities() {
        try {
            return activityDao.selectAllActivities();
        }catch (Exception e){
            log.error("ProductService -> allActivity() -> {}",e);
            throw new ServerException("获取活动信息->数据库出现异常");
        }
    }

    /**
     * 搜索相关信息
     * @param input
     * @return
     */
    @Override
    public List<CourseVo> search(String input) {
        List<CourseVo> result = new ArrayList<>();
        List<Integer> categoryId = categoryDao.selectCategories(input);
        if (categoryId.size() != 0) {
            List<CourseEntity> categoryCourseList = courseDao.selectCoursesByCategoryIds(categoryId);
            categoryCourseList.forEach(courseEntity -> courseEntity.setTeacherIntroduction(teacherDao.selectTeacherById(courseEntity.getTeacherId())));
            List<CourseVo> courseVoList = TransformUtil.transformList(categoryCourseList, new ArrayList<>(), CourseVo.class);
            courseVoList.forEach(courseVo -> result.add(courseVo));
        }
            List<CourseEntity> courseEntityList = courseDao.selectCoursesByCourseName(input);
            courseEntityList.forEach(courseEntity -> courseEntity.setTeacherIntroduction(teacherDao.selectTeacherById(courseEntity.getTeacherId())));
        if (courseEntityList.size() != 0){
            List<CourseVo> courseVoList1 = TransformUtil.transformList(courseEntityList,new ArrayList<>(),CourseVo.class);
            courseVoList1.forEach(courseVo -> result.add(courseVo));
        }
        return result;
    }

    /**
     * 按类别查询后的课程
     * @param categoryId
     * @return 查询按类别分类的课程集合
     */
    private List<CourseVo> getAllCategoryCourses(Integer categoryId){
        try {
            List<CourseVo> courseVoList = new ArrayList<>();
            List<CourseEntity> courseEntityList = courseDao.selectCoursesByCategoryId(categoryId);
            System.out.println(courseEntityList);
            courseEntityList.forEach(courseEntity ->{
                System.out.println(teacherDao.selectTeacherById(courseEntity.getTeacherId()));
                courseEntity.setTeacherIntroduction(teacherDao.selectTeacherById(courseEntity.getTeacherId()));
            });
            TransformUtil.transformList(courseEntityList,courseVoList,CourseVo.class);
            return courseVoList;
        }catch (Exception e){
            log.error("ProductService -> getAllCategoryCourses()->{}",e.getMessage());
            throw new ServerException("按类别获取课程->数据库出现异常");
        }
    }

    /**
     * 计算当前位置与各机构的距离
     * @param institutionEntityList
     * @return 计算完成并进行课程、距离赋值后的机构结果
     */
    private List<InstitutionVo> getDistanceResult(List<InstitutionEntity> institutionEntityList){
        try {
            //获取当前ip所在位置经纬度
            Location location = distanceUtil.getLocation();
            //拼接
            StringBuilder builder = new StringBuilder();
            for (InstitutionEntity institutionEntity : institutionEntityList) {
                builder.append(institutionEntity.getLat() + "," + institutionEntity.getLng() + ";");
            }
            String params = builder.toString().substring(0, builder.length() - 1);
            return getDistanceVo(institutionEntityList,distanceUtil.getDistance(params,location));
        }catch (Exception e){
            log.error("ProductServiceImpl -> getDistanceResult()：{}",e);
            throw new ThirdPartyServiceException("调取腾讯地图接口异常！");
        }

    }


    /**
     * 将调用腾讯地图API的返回结果与机构记过进行组装转换返回
     * @param institutionEntityList
     * @param mapResult
     * @return 返回组装api返回结果和课程的结果
     */
    private List<InstitutionVo> getDistanceVo(List<InstitutionEntity> institutionEntityList,TencentMapResult mapResult){
        try {
            List<InstitutionVo> institutionVoList = new ArrayList<>();
            //赋值计算出来的距离
            for (int i = 0; i < institutionEntityList.size(); i++) {
                institutionEntityList.get(i).setDistance(mapResult.getResult().getElements().get(i).getDistance());
            }
            TransformUtil.transformList(institutionEntityList,institutionVoList,InstitutionVo.class);
            //赋值机构课程
            return setCourseVoList(institutionVoList);
        }catch (Exception e){
            log.error("ProductServiceImpl -> getDistanceVo()：{}",e);
            throw new ParameterException("距离机构展示转化出现异常！");
        }
    }


    /**
     * 给机构赋值属于他的课程
     * @param institutionVoList
     * @return
     */
    private List<InstitutionVo> setCourseVoList(List<InstitutionVo> institutionVoList){
        for (InstitutionVo institutionVo : institutionVoList) {
            List<CourseVo> courseVoList = new ArrayList<>();
            List<CourseEntity> courseEntityList = courseDao.selectCoursesByInstitutionId(institutionVo.getInstitutionId());
            courseEntityList.forEach(courseEntity ->{
                System.out.println(courseEntity.getTeacherId());
                courseEntity.setTeacherIntroduction(teacherDao.selectTeacherById(courseEntity.getTeacherId()));


        });
            TransformUtil.transformList(courseEntityList,courseVoList,CourseVo.class);
            institutionVo.setCourseVoList(courseVoList);
        }
        return institutionVoList;
    }
}
