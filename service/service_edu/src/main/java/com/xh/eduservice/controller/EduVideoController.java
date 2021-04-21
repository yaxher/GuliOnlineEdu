package com.xh.eduservice.controller;


import com.xh.commonutils.R;
import com.xh.eduservice.entity.vo.VideoInfoVo;
import com.xh.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping("save")
    public R saveVideo(@RequestBody VideoInfoVo videoInfoVo){
        eduVideoService.saveVideoInfo(videoInfoVo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("getVideoInfo/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        VideoInfoVo videoInfoVo = eduVideoService.getVideoInfoVoById(id);
        return R.ok().data("items", videoInfoVo);
    }

    @ApiOperation(value = "更新课时")
    @PostMapping("updateVideoInfo")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoVo videoInfoVo
            ){

        eduVideoService.updateVideoInfoById(videoInfoVo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("removeVideo/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        return eduVideoService.removeVideoById(id) ? R.ok() : R.error().message("删除失败");

    }


}

