package cn.edu.cqupt.mis.colorfullcloud.service.serviceimpl;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.AuthenticationException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import cn.edu.cqupt.mis.colorfullcloud.dao.ChildrenDao;
import cn.edu.cqupt.mis.colorfullcloud.dao.UserDao;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.SuggestionDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.UserDto;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ChildrenEntity;
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
import java.util.List;

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
    private ChildrenDao childrenDao;

    /**
     * 用户登录
     * @param code
     * @return userVo
     */
    @Transactional(rollbackFor = SQLException.class)
    @Override
    public UserVo userLogin(String code,UserDto userDto) {
        try {
            OpenId openId = weChatUtil.getOpenId(code);
            log.info("获取到微信用户openId：{}", openId);
            String openid = openId.getOpenid();
            //在登陆态中，取出Redis中的缓存数据直接返回
/*            if(cacheUtil.flagRedis(openid)){
                UserEntity userEntity = (UserEntity) redisUtil.get(openid);
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(userEntity,userVo);
                return userVo;
            }*/
            return flagUser(openid,userDto);
        }catch (Exception e){
            log.error(this.getClass().getSimpleName()+"->userLogin()：{}",e);
            throw new ServerException("登陆出现异常！请联系管理员！");
        }

    }

    /**
     * 修改用户信息
     * @param userDto
     * @return userVo
     */
    @Override
    public UserVo modifyUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        UserVo userVo = new UserVo();
        /*if (redisUtil.get() == null){
            log.warn("用户未授权！");
            throw new AuthenticationException("未进行授权登录!!");
        }else */
            BeanUtils.copyProperties(userDto,userEntity);
            ServiceUtil.checkSqlExecuted(userDao.updateUser(userEntity));
            userEntity = userDao.selectUserById(userDto.getUserId());
            BeanUtils.copyProperties(userEntity,userVo);
            redisUtil.set(userEntity.getOpenid(),userEntity);
        return userVo;
    }

    /**
     * 用户注销
     * @param request
     */
    @Override
    public boolean userLogOut(HttpServletRequest request) {
        /*try {
            //String cacheKey = cacheUtil.getSessionId(request);
            UserEntity redisCache = (UserEntity) redisUtil.get(cacheKey);
            if (redisCache != null){
                redisUtil.delete(cacheKey);
                log.info("用户{}已注销",redisCache.getOpenid());
            }
            cacheUtil.deleteCookies(request);
            return true;
        }catch (Exception e){
            log.error("注销用户出现异常{}",e);
            throw new ServerException("注销账户出现异常！联系管理员！");
        }*/
        return true;
    }

    /**
     * 创建孩子信息
     * @param userId 用户id
     * @param childrenEntityList 孩子信息列表
     * @return 创建后的孩子信息列表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ChildrenEntity> createChildren(Integer userId,List<ChildrenEntity> childrenEntityList) {
        try {
            if (childrenEntityList == null) {
                return childrenDao.selectAllChildrenByUserId(userId);
            }
            ServiceUtil.checkSqlExecuted(childrenDao.insertChildrenByUserId(childrenEntityList));
            return childrenDao.selectAllChildrenByUserId(userId);
        }catch (Exception e){
            log.error("UserService -> createChildren() -> {}",e);
            throw new ServerException("获取孩子信息出现异常！");
        }

    }

    /**
     * 查询孩子是否可用(已登记的，不可用返回false)
     * @param idCard
     * @return true / false
     */
    @Override
    public Boolean judgeChildren(String idCard) {
        return childrenDao.selectAllChildrenByCard(idCard) == null;
    }

    /**
     * 删除孩子信息
     * @param userId 用户id
     * @param childrenIdList 孩子id列表
     * @return 孩子信息
     */
    @Override
    public List<ChildrenEntity> deleteChildren(Integer userId,List<Integer> childrenIdList) {
        ServiceUtil.checkSqlExecuted(childrenDao.deleteChildrenByChildrenIdList(childrenIdList));
        return childrenDao.selectAllChildrenByUserId(userId);
    }

    /**
     * 创建意见反馈
     *
     * @param suggestionDto 意见反馈
     * @return
     */
    @Override
    public Boolean createSuggestion(SuggestionDto suggestionDto) {
        try {
            ServiceUtil.checkSqlExecuted(userDao.insertSuggestion(suggestionDto));
            return true;
        }catch (Exception e){
            log.error("UserService -> createSuggestion() -> {}",e);
            throw new ServerException("意见反馈失败！！");
        }
    }

    /**
     * 查询孩子信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<ChildrenEntity> allChildren(Integer userId) {
        return childrenDao.selectAllChildrenByUserId(userId);
    }


    /**
     * 判断用户是否已经存在于数据库
     * @param openid
     * @return
     */
    private UserVo flagUser(String openid,UserDto userDto){
        try {
            UserEntity userEntity = new UserEntity();
            //若该用户不存在先进行注册
            if(userDao.selectUserByOpenId(openid) == null){
                userEntity.setOpenid(openid);
                TransformUtil.transformOne(userDto,userEntity);
                ServiceUtil.checkSqlExecuted(userDao.insertUser(userEntity));
            }
            //用户存在进行查询返回
            userEntity = userDao.selectUserByOpenId(openid);
            //cacheUtil.addRedis(openid,userEntity);
            UserVo userVo = (UserVo) TransformUtil.transformOne(userEntity,new UserVo());
            userVo.setChildrenMessage(childrenDao.selectAllChildrenByUserId(userVo.getUserId()));
            return userVo;
        }catch (Exception e){
            log.error("方法flagUser：{}",e);
            throw new ServerException("登录数据库操作异常！！");
        }
    }



}
