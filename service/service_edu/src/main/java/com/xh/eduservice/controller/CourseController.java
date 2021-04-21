package com.xh.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.commonutils.JwtUtils;
import com.xh.commonutils.R;
import com.xh.eduservice.client.OrderClient;
import com.xh.eduservice.entity.EduCourse;
import com.xh.eduservice.entity.vo.ChapterVo;
import com.xh.eduservice.entity.vo.CourseInfoVo;
import com.xh.eduservice.entity.vo.CourseQueryVo;
import com.xh.eduservice.entity.vo.CourseWebVo;
import com.xh.eduservice.service.EduChapterService;
import com.xh.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/courseFont")
@CrossOrigin
public class CourseController {

    @Autowired
    EduCourseService eduCourseService;

    @Autowired
    EduChapterService eduChapterService;

//    @Autowired
//    OrderClient orderClient;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, courseQuery);
        return  R.ok().data(map);
    }



    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
//        String memId = JwtUtils.getMemberIdByJwtToken(request);
//        boolean buyCourse = orderClient.isBuyCourse(memId, courseId);
//        System.out.println(memId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }

    @GetMapping("getDto/{courseId}")
    public com.xh.commonutils.vo.CourseWebVo getCourseInfoDto(@PathVariable("courseId") String courseId) {
        System.out.println(courseId);
        CourseWebVo courseInfoForm = eduCourseService.selectInfoWebById(courseId);
        com.xh.commonutils.vo.CourseWebVo courseInfoVo = new com.xh.commonutils.vo.CourseWebVo();
        BeanUtils.copyProperties(courseInfoForm,courseInfoVo);
        return courseInfoVo;
    }

//    //根据id查询课程详情信息
//    @GetMapping("getCourseInfo/{id}")
//    public R getCourseInfo(@PathVariable String id, HttpServletRequest request) {
//        //课程查询课程基本信息
//        CourseWebVo courseFrontInfo = eduCourseService.getFrontCourseInfo(id);
//        //查询课程里面大纲数据
//        List<ChapterVo> chapterVideoList = eduCourseService.getChapterVideoById(id);
//
//        //远程调用，判断课程是否被购买
//        boolean buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), id);
//
//        return R.ok().data("courseFrontInfo",courseFrontInfo).data("chapterVideoList",chapterVideoList).data("isbuy",buyCourse);
//    }
}
