package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ChildrenEntity;
import org.apache.ibatis.annotations.Param;
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


    /**
     * 更新孩子信息（学校，年级）
     * @param childrenId 孩子id
     * @param school 学校
     * @param grade 年级
     * @return
     */
    Boolean updateChildren(@Param("childrenId")Integer childrenId,@Param("school") String school,@Param("grade") String grade);

    /**
     * 根据用户id和孩子id查询是否存在该孩子
     * @param userId
     * @param childrenId
     * @return
     */
    ChildrenEntity selectChildrenByUserIdAndChildrenId(@Param("userId")Integer userId,@Param("childrenId") Integer childrenId);
}
