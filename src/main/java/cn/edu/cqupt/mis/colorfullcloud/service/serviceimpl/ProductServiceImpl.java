package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.dao.CourseDao;
import cn.edu.cqupt.mis.colorfullcloud.dao.InstitutionDao;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.InstitutionEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.service.ProductService;
import cn.edu.cqupt.mis.colorfullcloud.util.TransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 默认方法获取所有产品
     * @return 产品集合
     */
    @Override
    public List<InstitutionVo> allDefaultProducts(){
        try {
            List<InstitutionVo> institutionVoList = new ArrayList<>();
            TransformUtil.transformList(getAllInstitutions(),institutionVoList,InstitutionVo.class);
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
        return null;
    }

    /**
     * 分类查询所有产品
     * @return 按类分组返回
     */
    @Override
    public List<InstitutionVo> allCategoryProducts() {
        return null;
    }


    /**
     * 获取所有机构
     * @return 所有机构含产品的集合
     */
    private List<InstitutionEntity> getAllInstitutions(){
        try {
            return institutionDao.selectAllInstitutions();
        }catch (Exception e){
            log.error("ProductService -> getAllInstitutions()->{}",e.getMessage());
            throw new ServerException("数据库出现异常");
        }
    }
}
