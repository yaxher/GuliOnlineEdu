package com.xh.eduservice.service;

import com.xh.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
public interface EduChapterService extends IService<EduChapter> {
        List<ChapterVo> nestedList(String courseId);
        boolean removeChapterById(String id);
        boolean removeByCourseId(String courseId);
}
