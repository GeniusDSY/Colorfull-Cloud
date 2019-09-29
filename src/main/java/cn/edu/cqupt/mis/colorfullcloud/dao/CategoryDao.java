package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/2 11:23
 * @desc :
 */
@Repository
public interface CategoryDao {

    /**
     * 查询所有类别
     * @return 返回所有类别的集合
     */
    List<CategoryEntity> selectAllCategories();

    /**
     * 新增类别
     * @param type 类别名称
     * @return true 添加成功 false 添加失败
     */
    Boolean insertCategory(String type);

    /**
     * 模糊查询分类
     * @param input
     * @return
     */
    List<Integer> selectCategories(String input);

}
