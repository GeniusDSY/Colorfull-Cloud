package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.InstitutionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/1 8:45
 * @desc : 机构数据库接口
 */
@Repository
public interface InstitutionDao {

    /**
     * 查询所有机构
     * @return 所有机构
     */
    List<InstitutionEntity> selectAllInstitutions();

}
