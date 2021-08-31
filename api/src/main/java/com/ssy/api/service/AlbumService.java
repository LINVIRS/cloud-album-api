package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.AlbumQueryDto;
import com.ssy.api.result.RestResult;

import java.util.List;

public interface AlbumService {

    /**
     * 手动新建相册
     *
     * @return
     */
    RestResult createAlbumByUserId(AlbumDto albumDto);

    /**
     * 根据id修改相册信息
     *
     * @param albumDto
     * @return
     */
    RestResult modifyAlbumById(AlbumDto albumDto);

    /**
     * 通过相册id删除相册
     *
     * @param albumId
     * @return
     */
    RestResult deleteAlbumById(int albumId);

    /**
     * 获取所有的相册信息，默认时间排序
     *
     * @return
     */
    RestResult getAllAlbumsByUserId(AlbumQueryDto dto);

    /**
     * 获取用户分类所有相册
     * @param userId
     * @return
     */

    RestResult getAllAlbumsByIdentify(Integer userId);
    /**
     * 通过相册id获取相册详情
     *
     * @param albumId
     * @return
     */
    RestResult getAlbumDetailById(int albumId);

    /**
     * 添加照片到相册
     *
     * @param ids
     * @return
     */
    RestResult addPhotoTOAlbum(List<Integer> ids, Integer AlbumId);
}

