package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.dto.UserDataDto;
import com.ssy.api.SQLservice.dto.UserDto;
import com.ssy.api.SQLservice.entity.User;
import com.ssy.api.SQLservice.vo.UserVo;

import java.util.List;


/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:59:06 
 * @Version: V1.0
 **/
public interface UserDao {

    /**
     * 用户登录
     * @param userDto
     * @return
     */
    User userLogin(UserDto userDto);

    /**
     * 根据手机号查找
     * @param phone
     * @return
     */
    User findUserByPhone(String phone);

    /**
     * 根据账户查找
     * @param account
     * @return
     */
    User findUserByAccount(String account);

    /**
     * 查找用户id
     * @param userId
     * @return
     */
    UserVo findUserData(int userId);

    /**
     * 修改用户资料
     * @param userDataDto
     * @return
     */
    int  updateUserData(UserDataDto userDataDto);



    /**
     * 查找所有用户id
     * @return
     */
    List<Integer> findAllId();

}
