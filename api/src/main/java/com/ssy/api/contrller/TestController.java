package com.ssy.api.contrller;

import com.chinamobile.cmss.sdk.response.bean.EngineClassify;
import com.ssy.api.SQLservice.entity.UserLike;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.UserLikeService;
import com.ssy.api.util.Base64ImageUtil;
import com.ssy.api.util.FileUtil.fastdfs.FileDfsUtil;
import com.ssy.api.utils.MyQrCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @ClassName: TestController @Description: TODO @Author: WangLinLIN @Date:
 * 2021/03/30 12:37:28  @Version: V1.0
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Api(tags = "用户 API 接口")
public class TestController {
  @Resource private UserLikeService userLikeService;
  @Resource private FileDfsUtil fileDfsUtil;

  @GetMapping("/test/sql")
  public List<UserLike> testsql() {
    System.out.println("执行成功");
    return userLikeService.findAll();
  }

  @GetMapping("/test/qr")
  public String getQr(@RequestParam String text, int width, int height) {
    System.out.println("执行成功");
    return MyQrCodeUtil.generateQrCode("你好", 50, 50);
  }
  /**
   * 接口测试
   *
   * @return
   */
  @GetMapping("/test")
  public String test() {
    return "测试接口成功";
  }

  @GetMapping("/test/image")
  public List<EngineClassify> testAdmin() {
    String property = System.getProperty("user.dir");
    System.out.println("执行成功");
    System.out.println("执行成功");
    System.out.println("执行成功");

    return Base64ImageUtil.getImageClassify(new File(property + "/classes/image/test.JPG"));
  }

  /** 文件上传 */
  @ApiOperation(value = "上传文件", notes = "测试FastDFS文件上传")
  @PostMapping("/test/upload")
  public RestResult uploadFile(@RequestParam("file") MultipartFile file) {
    String result;
    try {
      String path = fileDfsUtil.upload(file);
      if (!StringUtils.isEmpty(path)) {
        result = path;
      } else {
        result = "上传失败";
      }
    } catch (Exception e) {
      e.printStackTrace();
      result = "服务异常";
    }
    return new RestResultBuilder<>().success(result);
  }
}
