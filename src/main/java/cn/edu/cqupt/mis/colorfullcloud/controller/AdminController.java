package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.AdminDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.AdminVo;
import cn.edu.cqupt.mis.colorfullcloud.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author :DengSiYuan
 * @date :2019/9/23 19:39
 * @desc :
 */
@Api(tags = "管理员模块")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation("(已测)生成二维码图片")
    @GetMapping(value = "verifyCode")
    public void verifyCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        adminService.generateVerifyCode(request,response);
    }

    @ApiOperation("(已测)管理员登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "verifyCode",value = "验证码",dataType = "string"),
            @ApiImplicitParam(name = "adminId",value = "管理员账号",dataType = "string"),
            @ApiImplicitParam(name = "adminPassword",value = "管理员密码",dataType = "string")
    })

    @PostMapping("adminLogin")
    public ResponseEntity<AdminVo> adminLogin(String verifyCode,String adminId,String adminPassword, HttpServletRequest request, HttpServletResponse response){
        AdminVo adminVo = adminService.adminLogin(verifyCode, adminId,adminPassword,request,response);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL, adminVo);
    }

}
