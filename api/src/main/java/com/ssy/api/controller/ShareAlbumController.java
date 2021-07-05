package com.ssy.api.controller;

import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.ShareAlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * ShareAlbumController
 *
 * @author wf
 * @date 2021/4/30 16:06
 * @description
 */
@RestController
@RequestMapping("/share")
@Api(tags = "共享相册")
public class ShareAlbumController {
    @Resource
    private ShareAlbumService shareAlbumService;

    @ApiOperation(value = "查询所有共享相册")
    @PostMapping(value = "/all/{userId}")
    public RestResult getAllShareAlbums(@PathVariable int userId) {
        return new RestResultBuilder<>().success(shareAlbumService.selectAllShareAlbum(userId));
    }

    @ApiOperation(value = "创建共享相册")
    @GetMapping(value = "/create")
    public RestResult creatorShareAlbum(@RequestParam int userId, @RequestParam int albumId) {
        return new RestResultBuilder<>().success(shareAlbumService.creatorShareAlbum(userId, albumId));
    }

    @ApiOperation(value = "删除共享相册")
    @PostMapping(value = "/move/{id}")
    public RestResult delShareAlbum(@PathVariable int id) {
        shareAlbumService.deleteShareAlbum(id);
        return new RestResultBuilder<>().success("成功");
    }

    @ApiOperation(value = "加入共享相册")
    @GetMapping(value = "/join")
    public RestResult joinShareAlbum(@RequestParam String keyWord, @RequestParam int joinUserId) {
        return new RestResultBuilder<>().success(shareAlbumService.joinShareAlbum(keyWord, joinUserId));
    }
}
