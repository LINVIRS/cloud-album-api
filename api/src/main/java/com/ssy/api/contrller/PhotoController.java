package com.ssy.api.contrller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.log.Log;
import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/photo")
@Api(tags = "照片API接口")
public class PhotoController {
    @Resource
    private PhotoService photoService;


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
}
