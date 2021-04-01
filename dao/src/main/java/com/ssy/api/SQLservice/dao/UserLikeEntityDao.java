package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.entity.UserLike;

import java.util.List;

/**
 * @ClassName: test @Description: TODO @Author: WangLinLIN @Date: 2021/04/01 09:16:25  @Version:
 * V1.0
 */
public interface UserLikeEntityDao {
  List<UserLike> findAllUser();
}
