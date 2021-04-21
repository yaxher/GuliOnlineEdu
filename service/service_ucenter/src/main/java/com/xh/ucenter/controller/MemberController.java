package com.xh.ucenter.controller;


import com.xh.commonutils.JwtUtils;
import com.xh.commonutils.R;
import com.xh.commonutils.vo.UcenterMember;
import com.xh.servicebase.exceptionhandler.GuliException;
import com.xh.ucenter.entity.Member;
import com.xh.ucenter.entity.vo.LoginInfoVo;
import com.xh.ucenter.entity.vo.LoginVo;
import com.xh.ucenter.entity.vo.RegisterVo;
import com.xh.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Yaher
 * @since 2021-04-15
 */
@RestController
@RequestMapping("/ucenterservice/apimember")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            System.out.println(loginInfoVo);
            return R.ok().data("item", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }

    //根据token字符串获取用户信息
    @PostMapping("getInfoUc/{id}")
    public com.xh.commonutils.vo.UcenterMember getInfo(@PathVariable("id") String id) {
        System.out.println(id);
        //根据用户id获取用户信息
        Member member = memberService.getById(id);
        com.xh.commonutils.vo.UcenterMember ucenterMember = new com.xh.commonutils.vo.UcenterMember();
        BeanUtils.copyProperties(member,ucenterMember);
        return ucenterMember;
    }
}
