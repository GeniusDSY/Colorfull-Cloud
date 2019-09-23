package cn.edu.cqupt.mis.colorfullcloud.controller;

import cn.edu.cqupt.mis.colorfullcloud.common.response.Response;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseEntity;
import cn.edu.cqupt.mis.colorfullcloud.common.response.ResponseStatu;
import cn.edu.cqupt.mis.colorfullcloud.domain.dto.*;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.ActivityEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.entity.TeacherEntity;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CategoryVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.CourseVo;
import cn.edu.cqupt.mis.colorfullcloud.domain.vo.InstitutionVo;
import cn.edu.cqupt.mis.colorfullcloud.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "获取所有机构信息")
    @GetMapping("allInstitutions")
    public ResponseEntity<List<InstitutionVo>> getAllInstitutions(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "上传机构信息")
    @PostMapping("createInstitution")
    public ResponseEntity<List<InstitutionVo>> createInstitution(@RequestBody InstitutionDto institutionDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "修改机构信息")
    @PostMapping("modifyActivities")
    public ResponseEntity<List<InstitutionVo>> modifyActivities(@RequestBody InstitutionDto institutionDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "删除机构信息(支持批量删除，均传list)")
    @DeleteMapping("deleteInstitution")
    public ResponseEntity<List<InstitutionVo>> deleteInstitution(@RequestBody List<Integer> institutionIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "获取所有课程信息")
    @GetMapping("allCourses")
    public ResponseEntity<List<CourseVo>> getAllCourses(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,productService.allCourses());
    }

    @ApiOperation(value = "上传课程信息")
    @PostMapping("createCourse")
    public ResponseEntity<List<CourseVo>> createCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("modifyCourse")
    public ResponseEntity<List<CourseVo>> modifyCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "删除课程信息(支持批量删除，均传list)")
    @DeleteMapping("deleteCourses")
    public ResponseEntity<List<CourseVo>> deleteCourses(@RequestBody List<Integer> courseIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "创建新类别")
    @PostMapping("createCategory")
    public ResponseEntity<List<CourseVo>> createCategory(CategoryDto categoryDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "获取所有类别信息")
    @GetMapping("allCategories")
    public ResponseEntity<List<CategoryVo>> getAllCategories(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "修改类别信息")
    @PostMapping("modifyCategory")
    public ResponseEntity<List<CategoryVo>> modifyCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "删除类别信息(支持批量删除，均传list)")
    @DeleteMapping("deleteCategories")
    public ResponseEntity<List<CategoryVo>> deleteCategories(@RequestBody List<Integer> categoryIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "创建新教师")
    @PostMapping("createTeacher")
    public ResponseEntity<List<TeacherEntity>> createTeacher(TeacherDto teacherDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "获取所有教师信息")
    @GetMapping("allTeachers")
    public ResponseEntity<List<TeacherEntity>> getAllTeachers(){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "修改教师信息")
    @PostMapping("modifyTeacher")
    public ResponseEntity<List<TeacherEntity>> modifyTeacher(@RequestBody TeacherDto teacherDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation(value = "删除教师信息(支持批量删除，均传list)")
    @DeleteMapping("deleteTeachers")
    public ResponseEntity<List<TeacherEntity>> deleteTeachers(@RequestBody List<Integer> categoryIdList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS, Response.SUCCESSFUL,null);
    }

    @ApiOperation("创建新活动")
    @PostMapping("createActivity")
    public ResponseEntity<List<ActivityEntity>> createActivity(@RequestBody ActivityDto activityDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,null);
    }

    @ApiOperation("删除活动(支持批量删除，均传list)")
    @DeleteMapping("deleteActivity")
    public ResponseEntity<List<ActivityEntity>> deleteActivity(@RequestBody List<Integer> activityList){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,null);
    }

    @ApiOperation("修改活动信息")
    @PostMapping("modifyActivity")
    public ResponseEntity<List<ActivityEntity>> modifyActivity(@RequestBody ActivityDto activityDto){
        return new ResponseEntity<>(ResponseStatu.SUCCESS,Response.SUCCESSFUL,null);
    }

}
