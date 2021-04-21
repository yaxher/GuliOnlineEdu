package com.xh.eduservice.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoVo {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;

    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;

    private String videoSourceId;
}
