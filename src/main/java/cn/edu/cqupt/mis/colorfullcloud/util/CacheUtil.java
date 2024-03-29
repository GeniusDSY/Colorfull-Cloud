package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.CacheKey;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.AuthenticationException;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ParameterException;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.UserEntity;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :DengSiYuan
 * @date :2019/8/15 22:37
 * @desc :
 */
@Slf4j
@Component
public class CacheUtil {

    @Resource
    private RedisUtil redisUtil;

    public String getSessionId(HttpServletRequest request){
        return request.getSession().getId();
    }

    /**
     * 增加用户信息到Redis
     * @param openid
     * @param userEntity
     */
    public void addRedis(String openid, UserEntity userEntity) {
        try {
            if(!redisUtil.set(openid, userEntity, CacheKey.CACHE_TIME)){
                throw new ParameterException("缓存存储出现异常");
            }
        }catch (Exception e){
            log.error(this.getClass().getSimpleName()+"addRedis:{}",e.getMessage());
            throw new ParameterException("缓存存储出现异常");
        }

    }

    /**
     * 判断用户是否已经存在于redis中
     * @param openid
     * */
    public boolean flagRedis(String openid){
        return redisUtil.get(openid) != null;
    }

    /**
     * @param request
     * @return
     */
    public String getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "---" + cookie.getValue());
            if (cookie.getName().equals(CacheKey.USER_ID)) {
                return cookie.getValue();
            }
        }
        throw new AuthenticationException("该用户未授权，请重新授权！");
    }

    /**
     * 销毁Cookie
     *
     * @param request
     */
    public void deleteCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CacheKey.USER_ID)) {
                cookie.setMaxAge(0);
            }
        }
    }

}
