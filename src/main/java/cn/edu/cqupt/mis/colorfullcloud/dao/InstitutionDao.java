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

    /**
     * 通过机构id查询机构信息
     * @param id
     * @return 所查机构信息
     */
    InstitutionEntity selectInstitutionById(Integer id);

    /**
     * 查询所传入数组中id的机构信息
     * @param institutionIds
     * @return
     */
    List<InstitutionEntity> selectInstitutionsByIdList(List<Integer> institutionIds);

    /**
     * 插入新机构信息
     * @param institutionEntity
     * @return
     */
    Boolean insertInstitution(InstitutionEntity institutionEntity);

}
