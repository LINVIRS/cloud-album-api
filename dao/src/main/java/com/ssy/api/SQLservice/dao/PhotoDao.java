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

    /**
     * 在回收站查找照片
     *
     * @return
     */
    List<Photo> findInTrashcan(PhotoDto photoDto);

    /**
     * 查找图片详情
     *
     * @param id 图片id
     * @return photo
     */
    Photo findDetailById(Integer id);

}
