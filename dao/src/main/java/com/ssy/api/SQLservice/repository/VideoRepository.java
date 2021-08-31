package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.VideoDao;
import com.ssy.api.SQLservice.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer>, VideoDao {
}
