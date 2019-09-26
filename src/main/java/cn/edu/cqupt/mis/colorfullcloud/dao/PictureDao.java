package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.PictureEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 21:03
 * @desc :
 */
@Repository
public interface PictureDao {

    /**
     * 查询机构的所有图片
     * @param institutionId 机构id
     * @return 机构图片地址集合
     */
    List<PictureEntity> selectInstitutionPicture(Integer institutionId);

    /**
     * 上传机构图片
     * @param pictureEntityList 图片信息列表
     * @return true/false
     */
    Boolean insertInstitutionPictures(List<PictureEntity> pictureEntityList);

}
