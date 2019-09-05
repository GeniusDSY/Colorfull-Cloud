package cn.edu.cqupt.mis.colorfullcloud.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/4/21 13:40
 * @desc :
 */
@Slf4j
public class TransformUtil {

    /**
     * 一个实体类之间的转换
     * @param src
     * @param target
     * @return target
     * */
    public static Object transformOne(Object src,Object target){
        Method[] srcMethods = src.getClass().getMethods();
        Method[] targetMethods = target.getClass().getMethods();
        for (Method srcMethod : srcMethods){
            String srcName = srcMethod.getName();
            if(srcName.startsWith("get")){
                try {
                    Object result = srcMethod.invoke(src);
                    for (Method targetMethod : targetMethods){
                        String targetName = targetMethod.getName();
                        if(targetName.startsWith("set") && targetName.substring(3,targetName.length())
                                .equals((srcName.substring(3, srcName.length())))){
                            targetMethod.invoke(target,result);
                        }
                    }
                } catch (Exception e) {
                    log.error("TransFromUtil->transformOne()：{}",e);
                }
            }
        }
        return target;
    }

    /**
     * 一个实体集合的转换
     * @param src
     * @param target
     * @param targetClass
     * @return list
     * */
    public static <S, T> List<T> transformList(List<S> src, List<T> target,
                                              Class<?> targetClass) {
        for (int i = 0; i < src.size(); i++) {
            try {
                Object object = targetClass.newInstance();
                target.add((T) object);
                transformOne(src.get(i), object);
            } catch (Exception e) {
                log.error("TransFromUtil->transformList()：{}",e);
                // 某个方法反射异常
                continue;
            }
        }
        return target;
    }
}
