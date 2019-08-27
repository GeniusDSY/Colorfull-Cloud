package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.AuthenticationException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.dao.UserDao;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.UserDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.OpenId;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.UserEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.UserVo;
import cn.edu.cqupt.mis.colorfullcloud.service.UserService;
import cn.edu.cqupt.mis.colorfullcloud.util.*;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:47
 * @desc :
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private WeChatUtil weChatUtil;

    @Resource
    private CacheUtil cacheUtil;

    /**
     * 用户登录
     * @param code
     * @return userVo
     */
    @Transactional(rollbackFor = SQLException.class)
    @Override
    public UserVo userLogin(String code, HttpServletRequest request, HttpServletResponse response) {
        try {
            //假定openid
            //OpenId openId = new OpenId();
            //openId.setOpenid("dwdafasfqweqwfasdad");
            OpenId openId = weChatUtil.getOpenId(code);
            log.info("获取到微信用户openId：{}", openId);
            //在登陆态中，取出Redis中的缓存数据直接返回
            if(cacheUtil.flagRedis(request)){
                UserEntity userEntity = (UserEntity) redisUtil.get(cacheUtil.getSessionId(request));
                System.out.println(userEntity);
                cacheUtil.addRedis(request,response,userEntity);
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(userEntity,userVo);
                return userVo;
            }
            return flagUser(openId.getOpenid(),request,response);
        }catch (Exception e){
            log.error(this.getClass().getSimpleName()+"->userLogin()：{}",e);
            throw new ServerException("登陆出现异常！请联系管理员！");
        }

    }

    /**
     * 修改用户信息
     *
     * @param userDto
     * @return userVo
     */
    @Override
    public UserVo modifyUser(UserDto userDto, HttpServletRequest request) {
        String cacheKey = cacheUtil.getSessionId(request);
        UserEntity userEntity = new UserEntity();
        UserVo userVo = new UserVo();
        //if (redisUtil.get(cacheKey) == null){
        //    log.warn("用户未授权！");
        //    throw new AuthenticationException("未进行授权登录!!");
        //}else {
            BeanUtils.copyProperties(userDto,userEntity);
            ServiceUtil.checkSqlExecuted(userDao.updateUser(userEntity));
            userEntity = userDao.selectUserById(userDto.getUserId());
            BeanUtils.copyProperties(userEntity,userVo);
        //}
        return userVo;
    }

    /**
     * 用户注销
     * @param request
     */
    @Override
    public boolean userLogOut(HttpServletRequest request) {
        try {
            String cacheKey = cacheUtil.getSessionId(request);
            UserEntity redisCache = (UserEntity) redisUtil.get(cacheKey);
            if (redisCache != null){
                redisUtil.delte(cacheKey);
                log.info("用户{}已注销",redisCache.getOpenid());
            }
            //cacheUtil.deleteCookies(request);
            return true;
        }catch (Exception e){
            log.error("注销用户出现异常{}",e);
            throw new ServerException("注销账户出现异常！联系管理员！");
        }
    }


    /**
     * 判断用户是否已经存在于数据库
     *
     * @param openid
     * @param request
     * @param response
     * @return
     */
    private UserVo flagUser(String openid, HttpServletRequest request, HttpServletResponse response){
        try {
            UserEntity userEntity = new UserEntity();
            //若该用户不存在先进行注册
            if(userDao.selectUserByOpenId(openid) == null){
                userEntity.setOpenid(openid);
                ServiceUtil.checkSqlExecuted(userDao.insertUser(userEntity));
            }
            //用户存在进行查询返回
            userEntity = userDao.selectUserByOpenId(openid);
            cacheUtil.addRedis(request,response,userEntity);
            return (UserVo) TransformUtil.transformOne(userEntity,new UserVo());
        }catch (Exception e){
            log.error("方法flagUser：{}",e);
            throw new ServerException("登录数据库操作异常！！");
        }
    }



}
