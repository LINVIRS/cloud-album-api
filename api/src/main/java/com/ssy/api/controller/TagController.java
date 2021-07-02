package com.ssy.api.controller;

import com.ssy.api.result.RestResult;
import com.ssy.api.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

/**
 * @ClassName: TagController
 * @Description: 标签控制
 * @Author: WangLinLIN
 * @Date: 2021/04/26 10:26:23 
 * @Version: V1.0
 **/
@RestController
@RequestMapping("/tag")
@Slf4j
@Api(tags = "标签 模块")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 新增tag接口
     * @param tagName
     * @param description
     * @param userId
     * @return
     */
    @ApiOperation(value = "新增标签", httpMethod = "POST", notes = "给图片添加标签")
    @PostMapping("/add")
    public RestResult addTag( @RequestParam String tagName, @RequestParam String description
    ,@RequestParam Integer userId) {
        return tagService.addPhotoTag(tagName,description,userId);
    }


    /**
     * 删除tagId
     * @param tagId
     * @return
     */
    @ApiOperation(value = "删除标签", httpMethod = "GET", notes = "删除标签")
    @GetMapping("/delete")
    public RestResult deleteTag( @RequestParam Integer tagId) {
        return tagService.deleteTag(tagId);
    }



    /**
     * 查询用户tagId
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询用户标签", httpMethod = "GET", notes = "查询用户标签")
    @GetMapping("/find/tags")
    public RestResult findTag( @RequestParam Integer userId) {
        return tagService.findUserTags(userId);
    }
}
