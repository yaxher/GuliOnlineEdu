package com.xh.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.commonutils.R;
import com.xh.eduservice.entity.EduCourse;
import com.xh.eduservice.entity.EduTeacher;
import com.xh.eduservice.service.EduCourseService;
import com.xh.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public class IndexController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapper);
        System.out.println(courseList);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
