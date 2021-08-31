package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.vo.TagVo;

import java.util.List;

public interface TagDao {


    /**
     * 删除tag标签
     * @param tagId
     * @return
     */
    long deleteTag(int tagId);

    /**
     * 安装tag
     * @param userId
     * @return
     */
    List<TagVo> findTagList(int userId);
}
