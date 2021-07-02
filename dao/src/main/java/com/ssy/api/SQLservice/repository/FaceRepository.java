package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.FaceDao;
import com.ssy.api.SQLservice.entity.Face;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaceRepository extends JpaRepository<Face, Integer>, FaceDao {

}
