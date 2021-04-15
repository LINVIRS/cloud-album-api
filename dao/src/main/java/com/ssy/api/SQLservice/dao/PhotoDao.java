package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.entity.Photo;

import java.util.List;

public interface PhotoDao {
    /**
     * 分页查找所有照片
     *
     * @return
     */
    List<Photo> findAllPhoto(PhotoDto photoDto);
}
