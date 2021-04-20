package com.ssy.api.contrller;

import com.ssy.api.result.RestResult;
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

    @ApiOperation(value = "人像探测", notes = "人像探测")
    @PostMapping("/facedetect")
    public RestResult uploadFile(@RequestParam("photo") String photo) {
//        return faceService.faceDetect(photo);
        return null;
    }
}
