package com.ssy.api.controller;

import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.ClassificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/classification")
@Api(tags = "分类模块")
public class ClassificationController {
    @Resource
    private ClassificationService classificationService;


    @ApiOperation(value = "根据地点分类", notes = "")
    @PostMapping("/location")
    public RestResult locationClassification(@RequestParam("userId") Integer userId) {
        return new RestResultBuilder<>().success(classificationService.locationClassification(userId));
    }


    @ApiOperation(value = "根据地点分类", notes = "")
    @PostMapping("/face")
    public RestResult face() {
        return new RestResultBuilder<>().success(classificationService.faceClassification());
    }
}
