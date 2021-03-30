package com.ssy.api.contrller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController @Description: TODO @Author: WangLinLIN @Date:
 * 2021/03/30 12:37:28  @Version: V1.0
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Api(tags = "用户 API 接口")
public class TestController {

  /**
   * 接口测试
   *
   * @return
   */
  @GetMapping("/test")
  public String test() {
    return "测试接口成功";
  }
}
