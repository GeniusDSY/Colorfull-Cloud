package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.common.contants.Status;
import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ThirdPartyServiceException;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.InstitutionEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.Location;
import cn.edu.cqupt.mis.colorfullcloud.domain.wechatdomain.TencentMapResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :DengSiYuan
 * @date :2019/9/3 9:05
 * @desc : 距离计算
 */
@Slf4j
@Component
public class DistanceUtil {

    @Value("${tencent.map-key}")
    private String mapKey;
    @Value("${tencent.current-location}")
    private String currentLocationUrl;
    @Value("${tencent.calculated-distance}")
    private String calculatedDistanceUrl;

    /**
     * 获取机构与当前位置的距离
     * @param param 经纬度组成的字符串
     * @param location 当前位置
     * @return
     */
    public TencentMapResult getDistance(String param, Location location){
        Map <String,String> params = new HashMap<>();
        params.put(Status.FROM,location.toString());
        params.put(Status.TO,param);
        params.put(Status.MAP_KEY,mapKey);
        String result = HttpClientUtil.doGet(calculatedDistanceUrl,params);
        log.info("DistanceUtil -> getDistance()：{}",result);
        TencentMapResult mapResult = JSON.parseObject(result, TencentMapResult.class);
        if(mapResult.getStatus() != 0){
            log.error("调用腾讯地图接口出现异常：",mapResult.getMessage());
            throw new ThirdPartyServiceException("计算距离失败！稍后重试！");
        }
        return mapResult;
    }

    /**
     * 获取当前ip的经纬度
     * @return 经纬度
     */
    public Location getLocation(){
        Map<String,String> params = new HashMap<>();
        params.put(Status.MAP_KEY,mapKey);
        String result = HttpClientUtil.doGet(currentLocationUrl,params);
        TencentMapResult mapResult = JSON.parseObject(result, TencentMapResult.class);
        if(mapResult.getStatus() != 0){
            log.error("调用腾讯地图接口出现异常：",mapResult.getMessage());
            throw new ThirdPartyServiceException("获取当前位置失败！稍后重试！");
        }
        System.out.println(mapResult);
        return mapResult.getResult().getLocation();
    }

}
