package com.ssy.api.controller;

import com.ssy.api.SQLservice.dto.face.SearchFaceDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.FaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/face")
@Api(tags = "人脸模块")
public class FaceController {
    @Resource
    private FaceService faceService;


    @ApiOperation(value = "人脸相册推送", notes = "人脸相册推送")
    @PostMapping("/recommend")
    public RestResult recommend(@RequestParam("photo") String photo) {
        Float v = faceService.faceMatch("http://36.137.109.33:8888/group1/M00/00/0A/rBEAA2B_veuANmAAAAAMfLlfZJE653.jpg",
                "http://36.137.109.33:8888/group1/M00/00/0A/rBEAA2B_u7mAA00jAAAMUPqty58836.jpg");
        return new RestResultBuilder<>().success(v);
    }

    @ApiOperation(value = "人脸搜索", notes = "人脸搜索")
    @GetMapping("/search")
    public RestResult search(@RequestParam String url, @RequestParam Integer faceSetId, @RequestParam Integer faceNumber) {
        List<SearchFaceDto> searchFaceDtos = faceService.searchFace(url, faceSetId, faceNumber);
        return new RestResultBuilder<>().success(searchFaceDtos);
    }


    @ApiOperation(value = "人脸搜索", notes = "人脸搜索")
    @PostMapping("/faceset")
    public RestResult createFaceSet(@RequestParam String url, @RequestParam Integer faceSetId, @RequestParam Integer faceNumber) {
        List<SearchFaceDto> searchFaceDtos = faceService.searchFace(url, faceSetId, faceNumber);
        return new RestResultBuilder<>().success(searchFaceDtos);
    }


}
