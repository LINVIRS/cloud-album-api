package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.dto.QueryDto;
import com.ssy.api.result.RestResult;

public interface AlbumService {

    /**
     * 手动新建相册
     * @return
     */
    RestResult createAlbumByUserId(AlbumDto albumDto);

    /**
     * 根据id修改相册信息
     * @param albumnId
     * @return
     */
    RestResult modifyAlbumById(AlbumDto albumDto);

    /**
     * 通过相册id删除相册
     * @param albumId
     * @return
     */
    RestResult deleteAlbumById(int albumId);

    /**
     * 获取所有的相册信息，默认时间排序
     * @return
     */
    RestResult getAllAlbumsByUserId(QueryDto queryDto, int userId, String sortStr, PageDto pageDto);

    /**
     * 通过相册id获取相册详情
     * @param albumId
     * @return
     */
    RestResult getAlbumDetailById(int albumId);


}

