package com.ssy.api.contrller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.AlbumService;
import com.ssy.api.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/photo")
@Api(tags = "照片API接口")
public class PhotoController {
    @Resource
    private PhotoService photoService;

    @Resource
    private AlbumService albumService;


    @ApiOperation(value = "查询所有图片", httpMethod = "POST", notes = "查询所有图片")
    @PostMapping("/all")
    public RestResult findAll(@RequestBody PhotoDto photoDto) {
        return photoService.findAll(photoDto);
    }

    @ApiOperation(value = "根据id查询图片", httpMethod = "POST", notes = "根据id查询图片")
    @PostMapping("/single")
    public RestResult findById(@RequestParam("id") Integer id) {
        return photoService.findById(id);
    }

    @ApiOperation(value = "保存图片", httpMethod = "POST", notes = "保存图片")
    @PostMapping("/saveall")
    public RestResult save(@RequestBody String photoDtosStr) {
        String s = photoDtosStr.replaceAll("\\\\", "");
        JSONObject jsonObject = JSON.parseObject(s);
        List<PhotoDto> photoDtos = JSON.parseArray(jsonObject.getString("str"), PhotoDto.class);
        return photoService.saveAll(photoDtos);
    }

    @ApiOperation(value = "删除照片", httpMethod = "POST", notes = "删除照片")
    @PostMapping("/delete")
    public RestResult delete(@RequestBody Integer[] ids) {
        return photoService.delete(Arrays.asList(ids));
    }

    @ApiOperation(value = "照片添加到相册", httpMethod = "POST", notes = "照片添加到相册")
    @PostMapping("/addtoalbum")
    public RestResult addPhotoTOAlbum(@RequestBody Integer[] ids) {
        System.out.println(ids);
        return albumService.addPhotoTOAlbum(Arrays.asList(ids), ids[0]);
    }


    @ApiOperation(value = "批量上传图片", httpMethod = "POST", notes = "批量上传图片")
    @PostMapping("/batch")
    public RestResult batchUpload(@RequestBody String photoDtosStr) {
        String s = photoDtosStr.replaceAll("\\\\", "");
        JSONObject jsonObject = JSON.parseObject(s);
        List<PhotoDto> photoDtos = JSON.parseArray(jsonObject.getString("str"), PhotoDto.class);
        return photoService.batchUploadPicture(photoDtos);
    }
}
