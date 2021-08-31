package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.UserDao;
import com.ssy.api.SQLservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserRepository
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:58:57 
 * @Version: V1.0
 **/
public interface UserRepository extends JpaRepository<User,Integer>, UserDao {
}
