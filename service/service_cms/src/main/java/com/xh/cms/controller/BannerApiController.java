package com.xh.cms.controller;

import com.xh.cms.entity.CrmBanner;
import com.xh.cms.service.CrmBannerService;
import com.xh.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

import java.util.List;


@RestController
@RequestMapping("/educms/banner")
@Api(description = "网站首页Banner列表")
@CrossOrigin //跨域
public class BannerApiController {
    @Autowired
    private CrmBannerService bannerService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<CrmBanner> list = bannerService.selectIndexList();
//        System.out.println(list);
//        System.out.println(redisTemplate.keys("*"));
//        System.out.println(redisTemplate.opsForValue().get("banner::selectIndexList"));
        return R.ok().data("bannerList", list);
    }
}
