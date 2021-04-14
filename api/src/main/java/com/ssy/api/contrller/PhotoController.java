package com.ssy.api.contrller;

import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.PhotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/photo")
@Api(tags = "照片API接口")
public class PhotoController {
    @Resource
    private PhotoService photoService;


    @ApiOperation(value = "查询所有图片", httpMethod = "POST", notes = "查询所有图片")
    @PostMapping("/all")
    public RestResult findAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setPage(page);
        photoDto.setPageSize(pageSize);
        return photoService.findAll(photoDto);
    }
}
