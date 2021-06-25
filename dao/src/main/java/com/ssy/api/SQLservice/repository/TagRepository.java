package com.ssy.api.SQLservice.repository;

import com.ssy.api.SQLservice.dao.TagDao;
import com.ssy.api.SQLservice.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer>, TagDao {
}
