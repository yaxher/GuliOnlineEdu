package com.xh.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.eduservice.entity.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfoVoById(String courseId);

    String updateCourseInfoById(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    List<EduCourse> selectByTeacherId(String id);

    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    CourseWebVo selectInfoWebById(String courseId);

    void updatePageViewCount(String id);
}
