package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:44
 * @desc :
 */
@Repository
public interface UserDao {

    /**
     * 根据open查询用户信息
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
    boolean updateUser(UserEntity userEntity);
}
