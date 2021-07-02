package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.UserLikeEntityDao;
import com.ssy.api.SQLservice.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserLikeRepository
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/01 09:21:16 
 * @Version: V1.0
 **/
public interface UserLikeRepository extends JpaRepository<UserLike,Integer>, UserLikeEntityDao {
}
