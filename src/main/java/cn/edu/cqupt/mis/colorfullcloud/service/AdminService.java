package cn.edu.cqupt.mis.colorfullcloud.service;

import cn.edu.cqupt.mis.colorfullcloud.domain.dto.AdminDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.AdminVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 19:54
 * @desc :
 */
@Service
public interface AdminService {

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    void generateVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 管理员登陆
     * @param verifyCode
     * @param adminId
     * @param adminPassword
     * @param request
     * @param response
     * @return 管理员信息
     */
    AdminVo adminLogin(String verifyCode,String adminId,String adminPassword, HttpServletRequest request, HttpServletResponse response);

}
