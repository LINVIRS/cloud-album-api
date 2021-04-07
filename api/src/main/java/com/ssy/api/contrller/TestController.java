package com.ssy.api.contrller;

import com.chinamobile.cmss.sdk.response.bean.EngineClassify;
import com.ssy.api.SQLservice.entity.UserLike;
import com.ssy.api.service.UserLikeService;
import com.ssy.api.util.Base64ImageUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Resource
    private UserLikeService userLikeService;


  @GetMapping("/test/sql")
  public List<UserLike> testsql() {
    System.out.println("执行成功");
    return userLikeService.findAll();
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

        return Base64ImageUtil.getImageClassify(
                new File(property + "/classes/image/test.JPG"));
    }
}
