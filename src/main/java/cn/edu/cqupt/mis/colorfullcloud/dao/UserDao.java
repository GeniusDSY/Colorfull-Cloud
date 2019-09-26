package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.dto.SuggestionDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.SuggestionEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:44
 * @desc :
 */
@Repository
public interface UserDao {

    /**
     * 根据openid查询用户信息
     * @param openid
     * @return userEntity
     * */
    UserEntity selectUserByOpenId(String openid);

    /**
     * 将新用户录入数据库
     * @param userEntity
     * @return true false
     * */
    boolean insertUser(UserEntity userEntity);

    /**
     * 更新用户资料
     * @param userEntity
     * @return true false
     * */
    Boolean updateUser(UserEntity userEntity);

    /**
     * 通过主键id查询用户信息
     * @param id 主键id
     * @return 用户信息
     */
    UserEntity selectUserById(Integer id);

    /**
     * 插入反馈意见
     * @param suggestionDto
     * @return
     */
    Boolean insertSuggestion(SuggestionDto suggestionDto);

    /**
     * 查询所有用户反馈
     * @return
     */
    List<SuggestionEntity> selectAllSuggestions();

}
