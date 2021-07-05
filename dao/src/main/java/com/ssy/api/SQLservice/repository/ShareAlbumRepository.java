package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.ShareAlbumDao;
import com.ssy.api.SQLservice.entity.ShareAlbum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ShareAlbumRepository
 *
 * @author wf
 * @date 2021/4/30 15:01
 * @description
 */
public interface ShareAlbumRepository extends JpaRepository<ShareAlbum, Integer>, ShareAlbumDao {
}
