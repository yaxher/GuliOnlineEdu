package com.xh.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xh.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.eduservice.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author hser
 * @since 2021-03-16
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    void pageConditionQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}
