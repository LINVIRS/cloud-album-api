package com.ssy.api.controller;

import com.ssy.api.SQLservice.vo.IdentifyVo;
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
@RequestMapping("/identify")
@Api(tags = "分类模块")
public class IdentifyController {

    @Resource
    private IdentifyService identifyService;
    @Resource
    private PictureService pictureService;

    @ApiOperation(value = "搜索照片", httpMethod = "POST", notes = "查询日期中的照片")
    @PostMapping("/photos/identify")
    public RestResult identifyPhotos(@RequestBody IdentifyVo identifyVo
    ) {
        List<PictureDocument> search = pictureService.search(identifyVo);
        return new RestResultBuilder<>().success(search);
    }

    @ApiOperation(value = "分类查询照片", httpMethod = "POST", notes = "分类查询照片")
    @PostMapping("/photos/classification")
    public RestResult classificationPhotos(@RequestBody IdentifyVo identifyVo
    ) {
        return identifyService.findPictureByType(identifyVo);
    }


    @ApiOperation(value = "查询分类", httpMethod = "POST", notes = "查询分类")
    @PostMapping("/photos/type")
    public RestResult findPhotosType(@RequestParam int userId
    ) {
        return identifyService.findPictureType(userId);
    }


    @ApiOperation(value = "查询分类", httpMethod = "POST", notes = "查询分类")
    @PostMapping("/photos/test")
    public RestResult test(@RequestParam int userId
    ) {
        return identifyService.identifyPicture(userId);
    }

    @ApiOperation(value = "自动生成视频", httpMethod = "POST", notes = "查询分类")
    @PostMapping("/video/create")
    public RestResult videoCreate(@RequestParam int userId) {
        return identifyService.findTypePicture(userId);
    }
}
