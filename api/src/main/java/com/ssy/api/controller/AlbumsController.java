package com.ssy.api.controller;

import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.AlbumQueryDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * AlbumsController
 *
 * @author wf
 * @date 2021/4/7 16:29
 * @description
 */
@RestController
@RequestMapping("/album")
@Slf4j
@Api(tags = "相册API接口")
public class AlbumsController {
    @Resource
    private AlbumService albumService;

    @ApiOperation(value = "新建相册接口", httpMethod = "POST", notes="新建相册接口")
    @PostMapping("/create")
    public RestResult createAlbumByUserId(@RequestBody AlbumDto albumDto) {
        return albumService.createAlbumByUserId(albumDto);
    }

    @ApiOperation(value = "根据id删除相册")
    @PostMapping("/remove/{id}")
    public RestResult deleteAlbumById(@PathVariable int id) {
        return albumService.deleteAlbumById(id);
    }

    @ApiOperation(value = "根据相册id修改相册信息")
    @PostMapping("/update")
    public RestResult modifyAlbumById(@RequestBody AlbumDto albumDto) {
        return albumService.modifyAlbumById(albumDto);
    }

    @ApiOperation(value = "根据id获取详情")
    @GetMapping("/detail/{id}")
    public RestResult getAlbumById(@PathVariable int id) {
        return albumService.getAlbumDetailById(id);
    }

    @ApiOperation(value = "获取用户所有的相册")
    @PostMapping("/all")
    public RestResult getAllAlbumsByUserId(@RequestBody AlbumQueryDto dto) {
        return albumService.getAllAlbumsByUserId(dto);
    }

    @PostMapping(value = "/test")
    public String test(@RequestParam String page, String index) {
        return "page:" + page + "index:" + index;
    }
}
