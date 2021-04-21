package com.xh.commonutils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Re {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private Re(){}

    //链式编程方法

    public static Re ok(){
        Re r = new Re();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static Re error(){
        Re r = new Re();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public Re success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Re message(String message){
        this.setMessage(message);
        return this;
    }

    public Re code(Integer code){
        this.setCode(code);
        return this;
    }

    public Re data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Re data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
