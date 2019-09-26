package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ActivityEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.ProductVo;
import cn.edu.cqupt.mis.colorfullcloud.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "产品模块")
@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;

    @ApiOperation("搜索产品")
    @GetMapping("searchProjects")
    public ResponseEntity<List<ProductVo>> searchProjects(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation("(已测)获取所有活动")
    @GetMapping("allActivities")
    public ResponseEntity<List<ActivityEntity>> allActivities(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allActivities());
    }

    @ApiOperation("(已测)获取机构信息和图片")
    @ApiImplicitParam(name = "institutionId",value = "机构id",dataType = "int")
    @GetMapping("allInstitutionPicture")
    public ResponseEntity<InstitutionVo> allInstitutionPicture(Integer institutionId){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allInstitutionPictures(institutionId));
    }

/*    @ApiOperation("(已测)按机构分类获取所有产品")
    @GetMapping("defaultAllProduct")
    public ResponseEntity<List<InstitutionVo>> defaultAllProduct(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allDefaultProducts());
    }*/

    @ApiOperation("(已测)按距离获取所有产品")
    @GetMapping("distanceAllProduct")
    public ResponseEntity<List<InstitutionVo>> distanceAllProduct(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allDistantProducts());
    }

/*    @ApiOperation("(已测)按类别获取所有产品")
    @GetMapping("categoryAllProduct")
    public ResponseEntity<List<CategoryVo>> categoryAllProduct(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allCategoryProducts());
    }*/

    @ApiOperation("(已测)获取所有产品")
    @GetMapping("allProducts")
    public ResponseEntity<List<CourseVo>> allProducts(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allCourses());
    }

}
