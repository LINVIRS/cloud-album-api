package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.PhotoDao;
import com.ssy.api.SQLservice.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer>, PhotoDao {

}
