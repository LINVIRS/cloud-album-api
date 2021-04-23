package com.ssy.api.contrller;

import com.ssy.api.SQLservice.dto.UserDataDto;
import com.ssy.api.SQLservice.dto.UserDto;
import com.ssy.api.SQLservice.vo.UserVo;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


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
        return userService.UserAccountCheck(account);
    }

    /**
     * 手机号检查接口
     * @param phone
     * @return
     */
    @ApiOperation(value = "手机号检查接口",httpMethod = "GET",notes = "手机号检查接口")
    @GetMapping("/phone/check")
    public RestResult userPhoneCheck(@RequestParam String  phone){
        return userService.UserPhoneCheck(phone);
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
    @ApiOperation(value = "查找用户资料",httpMethod = "GET",notes = "查找用户资料")
    @GetMapping("/user/{userId}")
    public UserVo userRegister(@PathVariable int userId){
        return userService.findUserData(userId);
    }

    /**
     * 修改用户资料接口
     * @param userDataDto
     * @return
     */
    @ApiOperation(value = "修改用户资料接口",httpMethod = "POST",notes = "修改用户资料接口")
    @PostMapping("/update/userData")
    public int userRegister(@RequestBody UserDataDto userDataDto){
        return userService.updateUserData(userDataDto);
    }

    /**
     * 退出用户登录
     * @param userId
     * @return
     */
    @ApiOperation(value = "退出用户登录",httpMethod = "GET",notes = "退出用户登录")
    @GetMapping("/logout")
    public RestResult UserLogout(@RequestParam int userId){
        return userService.UserLogout(userId);
    }
}
