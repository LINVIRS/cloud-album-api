package com.ssy.api.contrller;

import com.ssy.api.SQLservice.dto.UserDto;
import com.ssy.api.SQLservice.vo.UserVo;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.UserLikeService;
import com.ssy.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ResultSet;

/**
 * @ClassName: UserContoller
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 19:18:21 
 * @Version: V1.0
 **/
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户 API 接口")
public class UserContoller {

    @Resource
    private UserService userService;

    /**
     * 登录接口
     * @param userDto
     * @return
     */
    @ApiOperation(value = "用户登录接口",httpMethod = "POST",notes = "用户登录接口")
    @PostMapping("/login")
    public RestResult userLogin( @RequestBody UserDto userDto){
        return userService.UserLogin(userDto);
    }

    /**
     * 账户检查接口
     * @param account
     * @return
     */
    @ApiOperation(value = "用户账户检查接口",httpMethod = "GET",notes = "用户账户检查接口")
    @GetMapping("/account/check")
    public RestResult userAccountCheck(@RequestParam String  account){
        return userService.UserAcountCheck(account);
    }

    /**
     * 注册接口
     * @param userDto
     * @return
     */
    @ApiOperation(value = "用户注册接口",httpMethod = "POST",notes = "用户注册接口")
    @PostMapping("/register")
    public RestResult userRegister( @RequestBody UserDto userDto){
        return userService.UseRegistered(userDto);
    }

    /**
     * 用户资料接口
     * @param userId
     * @return
     */
//    @ApiOperation(value = "查找用户资料",httpMethod = "GET",notes = "查找用户资料")
//    @GetMapping("/user/{userId}")
//    public UserVo userRegister(@PathVariable int userId){
//        return userService.findUserData(userId);
//    }


}
