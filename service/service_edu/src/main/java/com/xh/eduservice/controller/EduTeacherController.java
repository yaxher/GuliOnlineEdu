package com.xh.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.commonutils.R;
import com.xh.eduservice.entity.EduTeacher;
import com.xh.eduservice.entity.vo.TeacherQuery;
import com.xh.eduservice.service.EduTeacherService;
import com.xh.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author hser
 * @since 2021-03-16
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(description = "讲师管理")
@CrossOrigin
public class EduTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;


    /**
     * 查询所有教师
     * @return 所有教师
     */
    @GetMapping("findAll")
    @ApiOperation("讲师列表")
    public R getAllTeachers(){
        List<EduTeacher> teachers = eduTeacherService.list(null);
        return R.ok().data("items", teachers);
    }

    /**
     * 删除讲师方法，通过路径中传递的id值进行传参
     * @param id 讲师id
     * @return 是否删除
     */
    @DeleteMapping("{id}")
    @ApiOperation("逻辑删除讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag)
            return R.ok();
        else
            return R.error();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{page}/{limit}")
    public R pageTeacher(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

//    @ApiOperation(value = "分页加条件讲师列表")
//    @PostMapping("pageConditionTeacher/{page}/{limit}")
//    public R pageConditionTeacher(
//            @ApiParam(name = "page", value = "当前页码", required = true)
//            @PathVariable Long page,
//
//            @ApiParam(name = "limit", value = "每页记录数", required = true)
//            @PathVariable Long limit,
//            TeacherQuery teacherQuery
//            ){
//
//
//        //创建分页
//        Page<EduTeacher> pageParam = new Page<>(page, limit);
//
////        //创建并封装查询条件
////        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
//
//        eduTeacherService.pageQuery(pageParam, teacherQuery);
//
//        List<EduTeacher> records = pageParam.getRecords();
//
//        long total = pageParam.getTotal();
//
//        return  R.ok().data("total", total).data("rows", records);
//    }


    @ApiOperation(value = "分页加条件讲师列表post")
    @PostMapping("pageConditionTeacher/{page}/{limit}")
    public R pageConditionTeacherPost(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery
    ){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.pageConditionQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        return eduTeacherService.save(teacher) ? R.ok() : R.error();

    }


    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacherById/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }


//    @ApiOperation(value = "根据ID修改讲师")
//    @PutMapping("updateTeacherById/{id}")
//    public R updateById(
//            @ApiParam(name = "id", value = "讲师ID", required = true)
//            @PathVariable String id,
//
//            @ApiParam(name = "teacher", value = "讲师对象", required = true)
//            @RequestBody EduTeacher teacher){
//
//        teacher.setId(id);
//        eduTeacherService.updateById(teacher);
//        return R.ok();
//    }

    @ApiOperation(value = "根据ID修改讲师Post")
    @PostMapping("updateTeacherByIdPost")
    public R updateByIdPost(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher
    ){
        return eduTeacherService.updateById(teacher) ? R.ok() : R.error();
    }


}

