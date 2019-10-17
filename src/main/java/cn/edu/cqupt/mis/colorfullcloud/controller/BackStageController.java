package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.SuggestionEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.ActivityVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.service.BackManageService;
import cn.edu.cqupt.mis.colorfullcloud.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :DengSiYuan
 * @date :2019/9/22 21:58
 * @desc :
 */
@Api(tags = "后台管理模块")
@RestController
@RequestMapping("backStageManager")
public class BackStageController {

    @Resource
    private ProductService productService;
    @Resource
    private BackManageService backManageService;

    @ApiOperation(value = "(已测)图片上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "图片类型1(机构icon),2(机构图片),3(课程icon)",allowableValues = "1,2,3",dataType = "int"),
            @ApiImplicitParam(name = "institutionId",value = "机构id",dataType = "int"),
            @ApiImplicitParam(name = "name",value = "图片名称",dataType = "string"),
            @ApiImplicitParam(name = "courseId",value = "课程id",dataType = "int")
    })
    @PostMapping("updateImage")
    public ResponseEntity updateImage(Integer fileType,Integer institutionId,String name,Integer courseId,@ApiParam(value = "上传的文件", required = true) MultipartFile file){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,backManageService.updateImages(fileType,institutionId,name,courseId,file));
    }

    @ApiOperation(value = "(已测)获取所有机构信息")
    @GetMapping("allInstitutions")
    public ResponseEntity<List<InstitutionVo>> getAllInstitutions(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.allInstitutions());
    }

    @ApiOperation(value = "(已测)上传机构信息")
    @PostMapping("createInstitution")
    public ResponseEntity<List<InstitutionVo>> createInstitution(@RequestBody InstitutionDto institutionDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.createInstitution(institutionDto));
    }

    @ApiOperation(value = "(已测)修改机构信息")
    @PostMapping("modifyInstitution")
    public ResponseEntity<List<InstitutionVo>> modifyInstitution(@RequestBody InstitutionDto institutionDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.modifyInstitution(institutionDto));
    }

    @ApiOperation(value = "(已测)删除机构信息(支持批量删除，均传list)")
    @DeleteMapping("deleteInstitution")
    public ResponseEntity<List<InstitutionVo>> deleteInstitution(@RequestBody List<Integer> institutionIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.deleteInstitution(institutionIdList));
    }

    @ApiOperation(value = "(已测)获取所有课程信息")
    @ApiImplicitParam(name = "institutionId",value = "机构id",dataType = "int")
    @GetMapping("allCourses")
    public ResponseEntity<List<CourseVo>> getAllCourses(Integer institutionId){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.allCourses(institutionId));
    }

    @ApiOperation(value = "(已测)上传课程信息")
    @PostMapping("createCourse")
    public ResponseEntity<List<CourseVo>> createCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.createCourse(courseDto));
    }

    @ApiOperation(value = "(已测)修改课程信息")
    @PostMapping("modifyCourse")
    public ResponseEntity<List<CourseVo>> modifyCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.modifyCourse(courseDto));
    }

    @ApiOperation(value = "(已测)删除课程信息(支持批量删除，均传list)")
    @ApiImplicitParam(name = "institutionId",value = "机构id",dataType = "int")
    @DeleteMapping("deleteCourses")
    public ResponseEntity<List<CourseVo>> deleteCourses(@RequestParam Integer institutionId,@RequestBody List<Integer> courseIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.deleteCourse(institutionId,courseIdList));
    }

    @ApiOperation(value = "(已测)创建新类别")
    @PostMapping("createCategory")
    public ResponseEntity<List<CategoryVo>> createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.createCategory(categoryDto));
    }

    @ApiOperation(value = "(已测)获取所有类别信息")
    @GetMapping("allCategories")
    public ResponseEntity<List<CategoryVo>> getAllCategories(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.allCategories());
    }

    @ApiOperation(value = "(已测)修改类别信息")
    @PostMapping("modifyCategory")
    public ResponseEntity<List<CategoryVo>> modifyCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.modifyCategory(categoryDto));
    }

    @ApiOperation(value = "(已测)删除类别信息(支持批量删除，均传list)")
    @DeleteMapping("deleteCategories")
    public ResponseEntity<List<CategoryVo>> deleteCategories(@RequestBody List<Integer> categoryIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.deleteCategory(categoryIdList));
    }

    @ApiOperation(value = "(已测)创建新教师")
    @PostMapping("createTeacher")
    public ResponseEntity<List<TeacherEntity>> createTeacher(@RequestBody TeacherDto teacherDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.createTeacher(teacherDto));
    }

    @ApiOperation(value = "(已测)获取所有教师信息")
    @GetMapping("allTeachers")
    public ResponseEntity<List<TeacherEntity>> getAllTeachers(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.allTeachers());
    }

    @ApiOperation(value = "(已测)修改教师信息")
    @PostMapping("modifyTeacher")
    public ResponseEntity<List<TeacherEntity>> modifyTeacher(@RequestBody TeacherDto teacherDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.modifyTeacher(teacherDto));
    }

    @ApiOperation(value = "(已测)删除教师信息(支持批量删除，均传list)")
    @DeleteMapping("deleteTeachers")
    public ResponseEntity<List<TeacherEntity>> deleteTeachers(@RequestBody List<Integer> teacherIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,backManageService.deleteTeacher(teacherIdList));
    }

    @ApiOperation("(已测)创建新活动")
    @PostMapping("createActivity")
    public ResponseEntity<List<ActivityVo>> createActivity(@RequestBody ActivityDto activityDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,backManageService.createActivity(activityDto));
    }

    @ApiOperation("(已测)删除活动(支持批量删除，均传list)")
    @DeleteMapping("deleteActivity")
    public ResponseEntity<List<ActivityVo>> deleteActivity(@RequestBody List<Integer> activityList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,backManageService.deleteActivity(activityList));
    }

    @ApiOperation("(已测)修改活动信息")
    @PostMapping("modifyActivity")
    public ResponseEntity<List<ActivityVo>> modifyActivity(@RequestBody ActivityDto activityDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,backManageService.modifyActivity(activityDto));
    }

    @ApiOperation("(已测)查询所有意见反馈")
    @GetMapping("allSuggestions")
    public ResponseEntity<List<SuggestionEntity>> allSuggestions(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,backManageService.getAllSuggestions());
    }

}
