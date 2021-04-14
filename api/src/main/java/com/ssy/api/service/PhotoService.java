package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.result.RestResult;

import java.util.List;

public interface PhotoService {
    /**
     * 分页查询所有照片
     *
     * @param photoDto
     * @return
     */
    RestResult findAll(PhotoDto photoDto);
}
