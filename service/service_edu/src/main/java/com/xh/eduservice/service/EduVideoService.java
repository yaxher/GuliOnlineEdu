package com.xh.eduservice.service;

import com.xh.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.eduservice.entity.vo.VideoInfoVo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String chapterId);
    void saveVideoInfo(VideoInfoVo videoInfoVo);

    VideoInfoVo getVideoInfoVoById(String id);

    void updateVideoInfoById(VideoInfoVo videoInfoVo);

    boolean removeVideoById(String id);
    boolean removeByCourseId(String courseId);
}
