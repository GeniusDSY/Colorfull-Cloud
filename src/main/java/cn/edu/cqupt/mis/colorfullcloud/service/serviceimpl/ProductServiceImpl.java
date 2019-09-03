package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ParameterException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ThirdPartyServiceException;
import cn.edu.cqupt.mis.colorfullcloud.dao.CategoryDao;
import cn.edu.cqupt.mis.colorfullcloud.dao.CourseDao;
import cn.edu.cqupt.mis.colorfullcloud.dao.InstitutionDao;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.CategoryEntity;
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
    private DistanceUtil distanceUtil;

    /**
     * 默认方法获取所有产品
     * @return 产品集合
     */
    @Override
    public List<InstitutionVo> allDefaultProducts(){
        try {
            List<InstitutionVo> institutionVoList = getAllInstitutions();
            for (InstitutionVo institutionVo : institutionVoList) {
                institutionVo.setCourseEntityList(courseDao.selectCoursesByInstitutionId(institutionVo.getInstitutionId()));
            }
            return institutionVoList;
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
            e.printStackTrace();
            log.error("ProductService -> allCategoryProducts()->{}",e.getMessage());
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
            throw new ServerException("数据库出现异常");
        }
    }

    private List<CourseVo> getAllInstitutionCourses(){
        try {
            List<CourseVo> courseVoList = new ArrayList<>();
            TransformUtil.transformList(courseDao.selectAllCourses(),courseVoList,CourseVo.class);
            return courseVoList;
        }catch (Exception e){
            log.error("ProductService -> getAllCourses()->{}",e.getMessage());
            throw new ServerException("数据库出现异常");
        }
    }

    private List<CourseVo> getAllCategoryCourses(Integer categoryId){
        try {
            List<CourseVo> courseVoList = new ArrayList<>();
            TransformUtil.transformList(courseDao.selectCoursesByCategoryId(categoryId),courseVoList,CourseVo.class);
            return courseVoList;
        }catch (Exception e){
            log.error("ProductService -> getAllCategoryCourses()->{}",e.getMessage());
            throw new ServerException("数据库出现异常");
        }
    }

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

    private List<InstitutionVo> getDistanceVo(List<InstitutionEntity> institutionEntityList,TencentMapResult mapResult){
        try {
            List<InstitutionVo> institutionVoList = new ArrayList<>();
            for (int i = 0; i < institutionEntityList.size(); i++) {
                institutionEntityList.get(i).setDistance(mapResult.getResult().getElements().get(i).getDistance());
            }
            TransformUtil.transformList(institutionEntityList,institutionVoList,InstitutionVo.class);
            for (InstitutionVo institutionVo : institutionVoList) {
                institutionVo.setCourseEntityList(courseDao.selectCoursesByInstitutionId(institutionVo.getInstitutionId()));
            }
            return institutionVoList;
        }catch (Exception e){
            log.error("ProductServiceImpl -> getDistanceVo()：{}",e);
            throw new ParameterException("距离机构展示转化出现异常！");
        }

    }
}
