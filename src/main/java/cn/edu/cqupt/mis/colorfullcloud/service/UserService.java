package cn.edu.cqupt.mis.colorfullcloud.service;

import cn.edu.cqupt.mis.colorfullcloud.domain.dto.UserDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:46
 * @desc : 用户Service
 */
@Service
public interface UserService {

    /**
     * 用户登录
     * @param code
     * @param request
     * @param response
     * @return userVo
     * */
    UserVo userLogin(String code, HttpServletRequest request, HttpServletResponse response);

    /**
     * 修改用户信息
     * @param userDto
     * @param request
     * @return userVo
     * */
    UserVo modifyUser(UserDto userDto, HttpServletRequest request);

    /**
     * 用户注销
     * @param request
     * */
    boolean userLogOut(HttpServletRequest request);
}
