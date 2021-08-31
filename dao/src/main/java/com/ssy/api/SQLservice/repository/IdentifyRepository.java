package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.IdentifyDao;
import com.ssy.api.SQLservice.entity.Identify;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @interface: IdentifyRepository
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 16:58:42 
 * @Version: V1.0
 **/
public interface IdentifyRepository extends JpaRepository<Identify,Integer>, IdentifyDao {
}
