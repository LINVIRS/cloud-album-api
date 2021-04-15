package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.PhotoDto;

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


    /**
     * 根据id查询照片
     *
     * @param id
     * @return
     */
    RestResult findById(Integer id);

    /**
     * 分页查询所有照片
     *
     * @param photoDtos
     * @return
     */
    RestResult saveAll(List<PhotoDto> photoDtos);

}
