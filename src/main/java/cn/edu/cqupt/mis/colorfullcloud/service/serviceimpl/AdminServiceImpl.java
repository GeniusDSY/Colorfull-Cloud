package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.CacheKey;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.AuthenticationException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ProcessException;
import cn.edu.cqupt.mis.colorfullcloud.dao.AdminDao;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.AdminDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.AdminEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.AdminVo;
import cn.edu.cqupt.mis.colorfullcloud.service.AdminService;
import cn.edu.cqupt.mis.colorfullcloud.util.EncryptionUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.TransformUtil;
import cn.edu.cqupt.mis.colorfullcloud.util.VerifyCodeImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 19:55
 * @desc :
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void generateVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object[] objs = VerifyCodeImageUtil.createImage();
        request.getSession().setAttribute(CacheKey.CODE_KEY, objs[0]);
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    /**
     * 管理员登陆
     * @param verifyCode 验证码
     * @param adminId 管理员账号
     * @param adminPassword 管理员密码
     * @param request
     * @param response
     * @return 管理员信息
     */
    @Override
    public AdminVo adminLogin(String verifyCode,String adminId,String adminPassword, HttpServletRequest request, HttpServletResponse response) {
        checkVerifyCode(verifyCode,request.getSession());
        AdminEntity adminEntity =
                adminDao.selectUserByAdminId(adminId);
        checkUser(adminPassword, adminEntity);
        String userSign = EncryptionUtil.md5(String.valueOf(adminEntity.getId()));
        request.getSession().setAttribute(CacheKey.USER_ID_KEY,userSign);
        //addCookie(response, userName, password);
        return (AdminVo) TransformUtil.transformOne(adminEntity,new AdminVo());
    }

    private void checkVerifyCode(String code, HttpSession session) {
        String verifyCode = (String) session.getAttribute(CacheKey.CODE_KEY);
        if (verifyCode == null) {
            throw new ProcessException("请先获取验证码图片");
        }
        if (!code.equalsIgnoreCase(verifyCode)) {
            throw new ProcessException("验证码有误");
        }
    }

    private void checkUser(String password, AdminEntity adminEntity) {
        if (adminEntity == null ||
                !adminEntity.getAdminPassword().equals(EncryptionUtil.md5(password))) {
            throw new AuthenticationException("请检查用户名或者密码是否有误");
        }
    }

   /* private void addCookie(HttpServletResponse response, String userName, String password) {
        Cookie userNameCookie = new Cookie(CookieKey.USER_NAME, userName);
        Cookie passwordCookie = new Cookie(CookieKey.PASSWORD, password);
        response.addCookie(userNameCookie);
        response.addCookie(passwordCookie);
    }*/

}
