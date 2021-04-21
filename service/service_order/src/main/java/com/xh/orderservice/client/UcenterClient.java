package com.xh.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据课程id查询课程信息
    @PostMapping("/ucenterservice/apimember/getInfoUc/{id}")
    public com.xh.commonutils.vo.UcenterMember getInfo(@PathVariable("id") String id);

//    @PostMapping("getInfoUc/{id}")
//    public com.xh.commonutils.vo.UcenterMember getInfo(@PathVariable String id)
}