package com.ssy.api.controller;

import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/video")
@Api(tags = "视频模块")
public class VideoController {
    @Resource
    private VideoService videoService;

    @ApiOperation(value = "查询所有视频", httpMethod = "POST", notes = "查询所有视频")
    @PostMapping("/all")
    public RestResult findAll(@RequestBody PageDto pageDto) {
        return videoService.findAll(pageDto);
    }
}
