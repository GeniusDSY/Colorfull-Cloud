package cn.edu.cqupt.mis.colorfullcloud.dao;

import cn.edu.cqupt.mis.colorfullcloud.domain.entity.AdminEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 22:06
 * @desc :
 */
@Repository
public interface AdminDao {

    /**
     * 查询管理员信息（登陆查询）
     * @param adminId 管理员账号
     * @return 管理员信息
     */
    AdminEntity selectUserByAdminId(String adminId);

}
