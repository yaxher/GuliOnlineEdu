package com.xh.eduservice.controller;


import com.xh.commonutils.R;
import com.xh.eduservice.entity.EduChapter;
import com.xh.eduservice.entity.vo.ChapterVo;
import com.xh.eduservice.service.EduChapterService;
import com.xh.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author hser
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getCourse/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }
    @ApiOperation("存储新章节")
    @PostMapping("saveChapter")
    public R save(@RequestBody EduChapter eduChapter){
        return eduChapterService.save(eduChapter) ? R.ok() : R.error();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("getChapterById/{id}")
    public R getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        EduChapter chapter = eduChapterService.getById(id);
        return R.ok().data("chapter", chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PostMapping("updateChapter")
    public R updateById(

            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter chapter){
        eduChapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除")
    @DeleteMapping("removeChapterById/{id}")
    public R removeChapterById(
            @PathVariable String id
    ){
        return eduChapterService.removeChapterById(id) ? R.ok() : R.error();
    }



}

