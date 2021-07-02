package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.FaceStoreDao;
import com.ssy.api.SQLservice.entity.FaceStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaceStoreRepository extends JpaRepository<FaceStore, Integer>, FaceStoreDao {
}
