package com.xh.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.eduservice.client.VodClient;
import com.xh.eduservice.entity.EduVideo;
import com.xh.eduservice.entity.vo.VideoInfoVo;
import com.xh.eduservice.mapper.EduVideoMapper;
import com.xh.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xh.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        Integer count = baseMapper.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    @Override
    public void saveVideoInfo(VideoInfoVo videoInfoVo) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoVo, video);
        if (videoInfoVo.getFree())
            video.setIsFree(1);
        else
            video.setIsFree(0);
        boolean result = this.save(video);

        if(!result){
            throw new GuliException(20001, "课时信息保存失败");
        }
    }

    @Override
    public VideoInfoVo getVideoInfoVoById(String id) {
        //从video表中取数据
        EduVideo video = this.getById(id);
        if(video == null){
            throw new GuliException(20001, "数据不存在");
        }

        //创建videoInfoForm对象
        VideoInfoVo videoInfoVo = new VideoInfoVo();
        BeanUtils.copyProperties(video, videoInfoVo);
        videoInfoVo.setFree(video.getIsFree() == 1);

        return videoInfoVo;
    }

    @Override
    public void updateVideoInfoById(VideoInfoVo videoInfoVo) {
        //保存课时基本信息
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoVo, video);
        if (videoInfoVo.getFree())
            video.setIsFree(1);
        else
            video.setIsFree(0);
        boolean result = this.updateById(video);
        if(!result){
            throw new GuliException(20001, "课时信息保存失败");
        }
    }

    @Override
    public boolean removeVideoById(String id) {
        //删除视频资源 TODO
        //查询云端视频id
        EduVideo video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        //删除视频资源
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        int result = baseMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        int count = baseMapper.delete(queryWrapper);
        return count > 0;
    }
}
