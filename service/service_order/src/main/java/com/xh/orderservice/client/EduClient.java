package com.xh.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("/eduservice/courseFont/getDto/{courseId}")
    public com.xh.commonutils.vo.CourseWebVo getCourseInfoDto(@PathVariable("courseId") String courseId);
}