package cn.edu.cqupt.mis.colorfullcloud.service;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ActivityEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.ProductVo;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/1 8:50
 * @desc : 产品服务接口类
 */
public interface ProductService {

    /**
     * 默认按照机构分类获取所有产品
     * @return 产品集合
     */
    List<InstitutionVo> allDefaultProducts();

    /**
     * 按照距离顺序排序所有产品
     * @return 按距离返回
     */
    List<InstitutionVo> allDistantProducts();

    /**
     * 分类查询所有产品
     * @return 按类分组返回
     */
    List<CategoryVo> allCategoryProducts();

    /**
     * 获取所有课程（不按机构分类）
     * @return 所有课程
     */
    List<CourseVo> allCourses();


    /**
     * 获取机构所有介绍图片
     * @param institutionId
     * @return 所有图片链接集合
     */
    InstitutionVo allInstitutionPictures(Integer institutionId);

    /**
     * 获取所有活动信息
     * @return 所有活动信息
     */
    List<ActivityEntity> allActivities();

    /**
     * 搜索相关信息
     * @param input
     * @return
     */
    List<CourseVo> search(String input);

    /**
     * 查询所有活动课程
     * @return
     */
    List<CourseVo> allActivityCourses();
}
