package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.AlbumDao;
import com.ssy.api.SQLservice.entity.Albums;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AlbumRepository
 *
 * @author wf
 * @date 2021/4/7 15:24
 * @description
 */
public interface AlbumRepository extends JpaRepository<Albums, Integer>, AlbumDao {
}
