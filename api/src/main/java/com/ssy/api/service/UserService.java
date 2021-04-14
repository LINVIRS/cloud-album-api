package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.UserDataDto;
import com.ssy.api.SQLservice.dto.UserDto;
import com.ssy.api.SQLservice.vo.UserVo;
import com.ssy.api.result.RestResult;

/**
 * @ClassName: UserService @Description: TODO @Author: WangLinLIN @Date:
 * 2021/04/06 18:08:32  @Version: V1.0
 */
public interface UserService {
  /**
   * 注册用户
   *
   * @param userDto
   * @return
   */
  RestResult UseRegistered(UserDto userDto);
  /**
   * 用户登录
   *
   * @param userDto
   * @return
   */
  RestResult UserLogin(UserDto userDto);

  /**
   * 账号检测
   *
   * @param account
   * @return
   */
  RestResult UserAcountCheck(String account);

  /**
   * 查询用户资料
   *
   * @param userId
   * @return
   */
  UserVo findUserData(int userId);

  /**
   * 修改用户资料
   *
   * @param userDataDto
   * @return
   */
  int updateUserData(UserDataDto userDataDto);

  /**
   * 用户退出
   *
   * @param userId
   * @return
   */
  RestResult UserLogout(int userId);
}
