package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.entity.Video;

import java.util.List;

public interface VideoDao {

    /**
     * 分页查找所有视频
     *
     * @return
     */
    List<Video> findAllVideo(PageDto pageDto);
}
