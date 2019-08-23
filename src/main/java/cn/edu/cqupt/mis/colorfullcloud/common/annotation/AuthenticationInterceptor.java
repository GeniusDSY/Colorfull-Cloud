/*
package cn.edu.cqupt.mis.colorfullcloud.common.annotation;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.AuthenticationException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

*/
/**
 * @author :DengSiYuan
 * @date :2019/5/1 17:54
 * @desc : 登录权限拦截器
 *//*

public class AuthenticationInterceptor implements HandlerInterceptor {

    private HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        UserLogin methodAnnotation = method.getAnnotation(UserLogin.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation == null) {
            return true;
        }else {
            Integer userId = (Integer) request.getSession().getAttribute(Status.USER_ID_KEY);
            // 执行认证
            if (userId == null) {
                throw new AuthenticationException("未登录！请登陆后操作！！");
            }
            */
/*UserEntity userEntity =  userDao.selectUserByUserId(userId);
            if(Arrays.asList(methodAnnotation.value()).contains(Identity.value(userEntity.getIdentity()))){
                throw new AuthentationException("对不起！您没有权限进行此操作!!");
            }*//*

            return true;
        }
    }
}
*/
