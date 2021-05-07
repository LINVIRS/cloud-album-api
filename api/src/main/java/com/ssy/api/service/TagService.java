package com.ssy.api.service;


import com.ssy.api.SQLservice.entity.Tag;
import com.ssy.api.result.RestResult;

import java.util.List;


public interface TagService {


    /**
     * 寻找标签
     *
     * @param ids
     * @return
     */
    List<Tag> selectTagsById(int[] ids);

    /**
     * 新增标签
     *
     * @param tagName
     * @param description
     * @param userId
     * @return
     */

    RestResult addPhotoTag(String tagName, String description, Integer userId);


    /**
     * 删除标签
     */
    RestResult deleteTag(int tagId);


    /**
     * 查用户标签
     *
     * @param userId
     * @return
     */
    RestResult findUserTags(int userId);


}

