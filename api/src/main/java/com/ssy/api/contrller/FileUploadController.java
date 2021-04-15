package com.ssy.api.contrller;

import com.alibaba.fastjson.JSON;
import com.ssy.api.SQLservice.dto.DownLoadFileDto;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.util.FileUtil.fastdfs.FileThreadTask;
import com.ssy.api.util.FileUtil.fastdfs.ThreakPoolFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: FileUploadController @Description: TODO @Author: WangLinLIN @Date:
 * 2021/04/11 10:48:26  @Version: V1.0
 */
@RestController
@RequestMapping("/file")
@Api(tags = "图片上传接口")
public class FileUploadController {

    @Resource
    private ThreakPoolFile threakPoolFile;
    @Resource
    private FileThreadTask fileThreadTask;

    @ApiOperation(value = "上传文件", notes = "测试FastDFS文件上传")
    @PostMapping("/uploadFile")
    public RestResult uploadFile(@RequestParam("files") MultipartFile[] multipartFiles) {
        List<String> result = threakPoolFile.getResultUpload(multipartFiles);
        return new RestResultBuilder<>().success(result);
    }

    @ApiOperation(value = "下载文件", notes = "测试FastDFS文件上传")
    @PostMapping("/downloadFile")
    public RestResult downloadFile(@RequestBody String downLoadFileDtoStr) {
        List<DownLoadFileDto> downLoadFileDtos = JSON.parseArray(downLoadFileDtoStr, DownLoadFileDto.class);
        downLoadFileDtos.forEach(i -> threakPoolFile.downloadFiles(i.getUrl(), i.getFullPath(), i.getFileName()));
        return new RestResultBuilder<>().success();
    }
}
