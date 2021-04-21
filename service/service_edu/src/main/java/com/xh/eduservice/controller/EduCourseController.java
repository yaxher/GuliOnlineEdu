package com.xh.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.eduservice.entity.EduCourse;
import com.xh.eduservice.entity.vo.CoursePublishVo;
import com.xh.eduservice.entity.vo.CourseQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import com.xh.commonutils.R;
import com.xh.eduservice.entity.vo.CourseInfoVo;
import com.xh.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Api(description = "上传课程")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("addCourseInfo")
    @ApiOperation("添加课程信息")
    public R addCourseInfo(@ApiParam(name = "courseInfo", value = "课程信息", required = true)
                               @RequestBody CourseInfoVo courseInfoVo){

        String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
        if(!StringUtils.isEmpty(courseId)){
            return R.ok().data("courseId", courseId);
        }else{
            return R.error().message("保存失败");
        }

    }

    @GetMapping("getCourseInfoById/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfoVoById(courseId);
        return R.ok().data("items", courseInfoVo);
    }


    @PostMapping("updateCourseInfoById")
    public R updateCourseInfoById(
//            @PathVariable String courseId,
            @RequestBody CourseInfoVo courseInfoVo
    ){
        String courseId = eduCourseService.updateCourseInfoById(courseInfoVo);
        return R.ok().data("courseId", courseId);
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("coursePublishInfo/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo courseInfoForm = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourse/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        return eduCourseService.publishCourseById(id) ? R.ok() : R.error();

    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("list/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery){

        Page<EduCourse> pageParam = new Page<>(page, limit);

        eduCourseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("removeCourse/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        return eduCourseService.removeCourseById(id) ? R.ok() : R.error();
    }



}

