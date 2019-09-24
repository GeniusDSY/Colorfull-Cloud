package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ChildrenEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/24 16:14
 * @desc :
 */
@Repository
public interface ChildrenDao {

    /**
     * 获取所有孩子
     * @param userId 家长id
     * @return 孩子信息列表
     */
    List<ChildrenEntity> selectAllChildrenByUserId(Integer userId);

    /**
     * 通过身份证号进行唯一性验证
     * @param card 身份证号
     * @return 孩子信息
     */
    ChildrenEntity selectAllChildrenByCard(String card);

    /**
     * 新增孩子
     * @param childrenEntityList 孩子列表
     * @return true / false
     */
    Boolean insertChildrenByUserId(List<ChildrenEntity> childrenEntityList);

    /**
     * 删除孩子列表
     * @param childrenIdList 孩子id列表
     * @return true / false
     */
    Boolean deleteChildrenByChildrenIdList(List<Integer> childrenIdList);


}
