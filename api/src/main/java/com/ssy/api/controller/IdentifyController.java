package com.ssy.api.controller;

import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.IdentifyService;
import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import com.ssy.api.util.allPictureUtils.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: IdentifyController
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 17:22:00 
 * @Version: V1.0
 **/
@RestController
@RequestMapping("/indetify")
@Api(tags = "分类模块")
public class IdentifyController {

    @Resource
    private IdentifyService identifyService;
    @Resource
    private PictureService pictureService;

    @ApiOperation(value = "搜索照片", httpMethod = "GET", notes = "查询日期中的照片")
    @GetMapping("/photos/identify")
    public RestResult identifyPhotos(@RequestParam int userId,@RequestParam String content,@RequestParam Integer pageSize,@RequestParam Integer pageIndex
    ) {
        List<PictureDocument> search = pictureService.search(content, userId, pageSize, pageIndex);
        return new RestResultBuilder<>().success(search);
    }

    @ApiOperation(value = "分类查询照片", httpMethod = "GET", notes = "分类查询照片")
    @GetMapping("/photos/classification")
    public RestResult classificationPhotos(@RequestParam int userId,@RequestParam String type,@RequestParam Integer pageSize,@RequestParam Integer pageIndex
    ) {
        return identifyService.findPictureByType(type,userId,pageSize,pageIndex);
    }


    @ApiOperation(value = "查询分类", httpMethod = "GET", notes = "查询分类")
    @GetMapping("/photos/type")
    public RestResult findPhotosType(@RequestParam int userId
    ) {
        return identifyService.findPictureType(userId);
    }
}
