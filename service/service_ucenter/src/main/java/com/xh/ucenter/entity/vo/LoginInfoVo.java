package com.xh.ucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="登录信息", description="注册对象")
public class LoginInfoVo {

    private String id;
    private Integer age;
    private String avatar;
    private String mobile;
    private String nickname;
    private Integer sex;
}
