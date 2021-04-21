package com.xh.eduservice.mapper;

import com.xh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.eduservice.entity.vo.CoursePublishVo;
import com.xh.eduservice.entity.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {


    CoursePublishVo getCoursePublishVoById(String id);

    CourseWebVo selectInfoWebById(String courseId);
}
