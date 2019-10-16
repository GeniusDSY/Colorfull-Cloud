package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.SuggestionDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.UserDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ChildrenEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.UserVo;
import cn.edu.cqupt.mis.colorfullcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ApiOperation("(已测)用户登录")
    @ApiImplicitParam(name = "code",value = "登录code",dataType = "string",required = true)
    @PostMapping("login")
    public ResponseEntity<UserVo> userLogin(String code, @RequestBody UserDto userDto){
        log.info("用户登陆code：{} + 信息->{}",code,userDto);
        UserVo userVo = userService.userLogin(code,userDto);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userVo);
    }

    @ApiOperation("(已测)修改用户信息")
    @PostMapping("modify")
    public ResponseEntity<UserVo> modifyUser(@RequestBody UserDto userDto){
        UserVo userVo = userService.modifyUser(userDto);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userVo);
    }

    @ApiOperation("(已测)创建孩子信息")
    @ApiImplicitParam(name = "userId",value = "用户id(本账号的id)",dataType = "int")
    @PostMapping("createChildren")
    public ResponseEntity<List<ChildrenEntity>> createChildren(@RequestParam Integer userId, @RequestBody List<ChildrenEntity> childrenEntityList){
        List<ChildrenEntity> childrenEntities = userService.createChildren(userId,childrenEntityList);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,childrenEntities);
    }

    @ApiOperation("(已测)查询孩子信息")
    @ApiImplicitParam(name = "userId",value = "用户id(本账号的id)",dataType = "int")
    @PostMapping("allChildren")
    public ResponseEntity<List<ChildrenEntity>> allChildren(Integer userId){
        List<ChildrenEntity> childrenEntities = userService.allChildren(userId);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,childrenEntities);
    }

    @ApiOperation("(已测)查询孩子是否可用(已登记的，不可用返回false)")
    @ApiImplicitParam(name = "idCard",value = "身份证号",dataType = "string")
    @PostMapping("judgeChildren")
    public ResponseEntity<Boolean> judgeChildren(@RequestParam String idCard){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userService.judgeChildren(idCard));
    }

    @ApiOperation("(已测)删除孩子信息(支持批量删除，均传list无法还原)")
    @ApiImplicitParam(name = "userId",value = "用户id(本账号的id)",dataType = "int")
    @DeleteMapping("deleteChildren")
    public ResponseEntity<List<ChildrenEntity>> deleteChildren(@RequestParam Integer userId,@RequestBody List<Integer> childrenIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userService.deleteChildren(userId,childrenIdList));
    }

    @ApiOperation("修改孩子信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",dataType = "int"),
            @ApiImplicitParam(name = "childrenId",value = "孩子id",dataType = "int"),
            @ApiImplicitParam(name = "school",value = "学校",dataType = "string"),
            @ApiImplicitParam(name = "grade",value = "年级",dataType = "string")
    })
    @PostMapping("updateChildren")
    public ResponseEntity<List<ChildrenEntity>> updateChildren(@RequestParam Integer userId,@RequestParam Integer childrenId,@RequestParam String school,@RequestParam String grade){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userService.updateChildren(userId,childrenId,school,grade));
    }

    @ApiOperation("(已测)新建用户反馈")
    @PostMapping("createSuggestion")
    public ResponseEntity<Boolean> createSuggestion(@RequestBody SuggestionDto suggestionDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,userService.createSuggestion(suggestionDto));
    }

    @ApiOperation("(已测)用户注销")
    @DeleteMapping("logout")
    public ResponseEntity<Boolean> userLogOut(HttpServletRequest request){
        boolean flag = userService.userLogOut(request);
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,flag);
    }

}
