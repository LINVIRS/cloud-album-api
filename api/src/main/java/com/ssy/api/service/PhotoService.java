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
     * 在回收站里查找照片
     *
     * @param photoDto
     * @return
     */
    RestResult findInTrashcan(PhotoDto photoDto);

    /**
     * 分页查询所有照片
     *
     * @param photoDtos
     * @return
     */
    RestResult saveAll(List<PhotoDto> photoDtos);


    /**
     * 删除照片
     *
     * @param ids
     * @return
     */
    RestResult delete(List<Integer> ids);

    /**
     * 批量上传文件
     *
     * @param photos 图片
     * @return Result
     */
    RestResult batchUploadPicture(List<PhotoDto> photos);

    /**
     * 添加照片标签
     *
     * @param photoId
     * @param tagName
     * @return
     */
    RestResult addPhotoTag(Integer photoId, String tagName,String description);



}
