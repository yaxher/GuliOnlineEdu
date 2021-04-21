package com.xh.eduservice.controller;


import com.xh.commonutils.R;
import com.xh.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author hser
 * @since 2021-03-20
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(description = "课程管理")
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;


    @PostMapping("addSubject")
    @ApiOperation("添加课程")
    public R addSubject(@ApiParam(name = "file", value = "课程列表Excel文件", required = true) MultipartFile file){
        eduSubjectService.importSubjectData(file, eduSubjectService);
        return R.ok();
    }

    @GetMapping("getSubject")
    @ApiOperation("获取课程列表")
    public R getSubject(){
        return R.ok().data("items", eduSubjectService.nestedList());
    }

}

