package com.ssy.api.service;


<<<<<<< Updated upstream
import com.ssy.api.SQLservice.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> selectTagsById(int[] ids);
=======
import com.ssy.api.result.RestResult;

public interface TagService {
    /**
     * 新增标签
     * @param tagName
     * @param description
     * @param userId
     * @return
     */
    RestResult addPhotoTag( String tagName, String description,Integer userId);


    /**
     * 删除标签
     */
    RestResult deleteTag(int tagId);


    /**
     *
     * 查用户标签
     * @param userId
     * @return
     */
    RestResult findUserTags(int userId);

>>>>>>> Stashed changes
}
