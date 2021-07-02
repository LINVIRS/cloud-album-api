package com.ssy.api.controller;

import com.alibaba.fastjson.JSON;
import com.ssy.api.SQLservice.dto.DownLoadFileDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.VideoService;
import com.ssy.api.util.FileUtil.fastdfs.FileThreadTask;
import com.ssy.api.util.FileUtil.fastdfs.ThreakPoolFile;

import com.ssy.api.utils.FileUploaderUtil;
import com.ssy.api.utils.photoExifUtil.PhotoExifVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FileUploadController @Description: TODO @Author: WangLinLIN @Date:
 * 2021/04/11 10:48:26  @Version: V1.0
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件操作模块")
public class FileUploadController {

    @Resource
    private ThreakPoolFile threakPoolFile;
    @Resource
    private FileThreadTask fileThreadTask;
    @Resource
    private VideoService videoService;




    @ApiOperation(value = "上传图片", notes = "图片上传")
    @PostMapping("/uploadFile")
    public RestResult uploadFile(@RequestParam("files") MultipartFile[] multipartFiles) {
        List<PhotoExifVo> result = threakPoolFile.getResultUpload(multipartFiles);

        return new RestResultBuilder<>().success(result);
    }


    @ApiOperation(value = "上传视频", notes = "视频上传")
    @PostMapping("/uploadvideo")
    public RestResult uploadVideo(@RequestParam("files") MultipartFile[] multipartFiles) throws Exception {
        Boolean aBoolean = false;
        List<String> videos = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            // 这里写了本地路径，之后改成项目服务器路径
            Path filepath = Paths.get("/Users/yy/Downloads", file.getOriginalFilename());

            try (OutputStream os = Files.newOutputStream(filepath)) {
                os.write(file.getBytes());
            }
            aBoolean = FileUploaderUtil.uploadVideo(filepath.toString());
            if (aBoolean) {
                String video = FileUploaderUtil.getVideo(filepath.toString());
                videos.add(video);
            }
        }
        return new RestResultBuilder<>().success(videos);
    }

    @ApiOperation(value = "下载文件", notes = "测试FastDFS文件上传")
    @PostMapping("/downloadFile")
    public RestResult downloadFile(@RequestBody String downLoadFileDtoStr) {
        List<DownLoadFileDto> downLoadFileDtos = JSON.parseArray(downLoadFileDtoStr, DownLoadFileDto.class);
        downLoadFileDtos.forEach(i -> threakPoolFile.downloadFiles(i.getUrl(), i.getFullPath(), i.getFileName()));
        return new RestResultBuilder<>().success();
    }

    @ApiOperation(value = "合并视频文件", notes = "")
    @PostMapping("/mergevideo")
    public RestResult mergeVideo(@RequestParam("files") MultipartFile[] files) {
        return new RestResultBuilder<>().success(videoService.mergeVideo(files));
    }

    @ApiOperation(value = "将图片转成视频", notes = "")
    @PostMapping("/pictovideo")
    public RestResult picTureToVideo(@RequestParam("files") MultipartFile[] files) {
        return new RestResultBuilder<>().success(videoService.pictureToVideo(files));
    }

    @ApiOperation(value = "给视频加上背景音乐", notes = "")
    @PostMapping("/addmuisc")
    public RestResult convertorWithBgmNoOriginCommon(@RequestParam("inputVideo") MultipartFile inputVideo, @RequestParam("inputMusic") MultipartFile inputMusic) {
        return new RestResultBuilder<>().success(videoService.convertorWithBgmNoOriginCommon(inputVideo, inputMusic));
    }
}
