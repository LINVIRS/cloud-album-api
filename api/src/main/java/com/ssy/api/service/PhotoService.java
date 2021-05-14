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
     * 根据用户id获取最近删除的图片
     *
     * @param userId
     * @return
     */
    RestResult getRecentDelPhoto(int userId);

    /**
     * 删除照片
     *
     * @param ids
     * @return
     */
    RestResult deleteThorough(List<Integer> ids);


    /**
     * 添加tag
     * @param photoId
     * @param tagId
     * @return
     */

    RestResult addPhotoTag(Integer photoId, Integer tagId);

    /**
     * 修改照片
     *
     * @param photoId
     * @param tagId
     * @return
     */
    RestResult deletePhotoTag(Integer photoId, Integer tagId);


    /**
     * 恢复照片
     *
     * @param ids
     * @return
     */
    RestResult recoverPhoto(List<Integer> ids);


    /**
     * 根据坐标查找范围照片
     * @param userId
     * @param longitude
     * @param latitude
     * @return
     */
    RestResult findPhotoByLocation(int userId,double longitude, double latitude);

    /**
     *  根据照片分类
     * @param userId
     * @return
     */
  RestResult findPhotoToClassification(int userId);




    /**
     *  根据照片分类
     * @param userId
     * @return
     */
    RestResult findPhotoByDay(int userId,String startTime,String endTime);
}