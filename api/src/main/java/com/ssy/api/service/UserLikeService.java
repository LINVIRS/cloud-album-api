package com.ssy.api.service;

import com.ssy.api.SQLservice.entity.UserLike;

import java.util.List;

/**
 * @ClassName: UserLike
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/01 09:22:27 
 * @Version: V1.0
 **/

public interface UserLikeService {
    List<UserLike> findAll();

}
