package com.ssy.api.contrller;

import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.FaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/face")
@Api(tags = "人脸接口")
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
}
