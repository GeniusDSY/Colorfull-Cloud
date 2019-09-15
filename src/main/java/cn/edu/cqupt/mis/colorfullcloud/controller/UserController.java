package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.UserDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.UserVo;
import cn.edu.cqupt.mis.colorfullcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:31
 * @desc : 用户模块Controller
 */
@Slf4j
@Api(tags = "用户模块")
@RequestMapping("userSystem")
@RestController
public class UserController {


    @Resource
    private UserService userService;

    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "code",value = "登录code",dataType = "string",required = true)
    @PostMapping("login")
    public ResponseEntity<UserVo> userLogin(String code, @RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response){
        log.info("用户登陆code：{} + 信息->{}",code,userDto);
        UserVo userVo = userService.userLogin(code,userDto);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userVo);
    }


    @ApiOperation("修改用户信息")
    @PostMapping("modify")
    public ResponseEntity<UserVo> modifyUser(@RequestBody UserDto userDto){
        System.out.println(userDto);
        UserVo userVo = userService.modifyUser(userDto);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userVo);
    }

    @ApiOperation("用户注销")
    @DeleteMapping("logout")
    public ResponseEntity<Boolean> userLogOut(HttpServletRequest request){
        boolean flag = userService.userLogOut(request);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,flag);
    }

}
