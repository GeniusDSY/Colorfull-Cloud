package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.service.ProductService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/8/27 16:51
 * @desc : 产品模块controller
 */
@Slf4j
@Api("产品模块")
@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;


    /**
     * 默认获取所有课程
     * @return 按机构分组数组
     */
    @GetMapping("defaultAllProduct")
    public ResponseEntity<List<InstitutionVo>> defaultAllProduct(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allDefaultProducts());
    }

    /**
     * 按距离远近获取所有课程
     * @return 按机构分组数组
     */
    @GetMapping("distanceAllProduct")
    public ResponseEntity<List<InstitutionVo>> distanceAllProduct(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allDistantProducts());
    }

    /**
     * 按类别获取远近获取所有课程
     * @return 按机构分组数组
     */
    @GetMapping("categoryAllProduct")
    public ResponseEntity<List<InstitutionVo>> categoryAllProduct(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }


}
