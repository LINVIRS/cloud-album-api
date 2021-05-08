package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.utils.Location;

import java.util.List;

/**
 * @author wanglin
 */
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

    /**
     * 查找出最近删除的图片
     */
    List<Photo> findRecentDelPhoto(int userId);

    /**
     * 根据经纬度查询范围内照片
     *
     * @param userId
     * @param location
     * @return
     */
    List<Photo> findSimilarPhotoByLocation(int userId, Location location);

    /**
     * 查询用户照片
     *
     * @param userId
     * @return
     */
    List<Photo> findPhoto(int userId);


    /**
     * 根据年来分类照片
     * @param userId
     * @return
     */
    List<String> findPhotoByGroupByTimeYear(int userId);
}
