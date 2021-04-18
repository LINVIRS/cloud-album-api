package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.AlbumQueryDto;
import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.entity.Photo;

import java.util.List;

/**
 * AlbumDao
 *
 * @author wf
 * @date 2021/4/7 13:31
 * @description
 */
public interface AlbumDao {

    /**
     * 通过用户id获取所有相册，默认时间降序
     * name: 昵称排序
     * createTime: 创建时间排序
     * updateTime: 修改时间排序
     *
     * @return List<Albums> albumList
     */
    List<Albums> getAllAlbumsByUserId(AlbumQueryDto dto);

    /**
     * 根据相册id查询相册详情
     *
     * @param albumId 相册id
     * @return List<Photo> list
     */
    Albums getAlbumDetailById(int albumId);

    /**
     * 根据id修改相册信息
     *
     * @param albumDto 相册视图
     * @param albumId  相册id
     * @return Albums
     */
    int updateAlbumById(AlbumDto albumDto, int albumId);
}
