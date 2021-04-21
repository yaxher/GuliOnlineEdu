package com.xh.eduservice.controller;


import com.xh.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
@Api(description = "用户登录")
public class EduLoginController {

    @PostMapping("login")
    @ApiOperation("登录")
    public R login(){
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    @ApiOperation("信息")
    public R info(){
        return R.ok().data("roles","{admin}").data("name", "黎明").data("avatar", "https://yaher.oss-cn-shanghai.aliyuncs.com/2021/03/20/2b2a02b6361b47baaa45b87fb9a7c174.png");
    }
}
