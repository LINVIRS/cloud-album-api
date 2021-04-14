package com.ssy.api.SQLservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserDto
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:32:51 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    //手机号
    private String phoneNumber;
  //验证码
    private  String verificationCode;
    //账户
    private String account;
   //密码
    private String password;
    //状态 0为手机验证码登录 1为账号密码登录
  private  int status;
}
