package com.ssy.api.SQLservice.dao;

import com.ssy.api.SQLservice.entity.ShareAlbum;

import java.util.List;

/**
 * ShareAlbum
 *
 * @author wf
 * @date 2021/4/30 14:36
 * @description
 */
public interface ShareAlbumDao {
    /**
     * 根据创建者、加入者id查询他所有共享的相册
     * @param userId
     * @return
     */
    List<ShareAlbum> selectAll(int userId);

    /**
     * 根据秘钥搜索共享相册
     * @param keyWord
     * @return
     */
    ShareAlbum getOneByKeyWord(String keyWord);

    /**
     * 查询是否已经为分享相册
     * @param userId
     * @param albumId
     * @return
     */
    ShareAlbum searchOneByCreatorIdAndAlbumId(int userId, int albumId);

    /**
     * 查询是否加入该共享相册
     * @param joinUserId
     * @param albumId
     * @return
     */
    ShareAlbum searchOneByJoinUserIdAndAlbumId(int joinUserId, String keyWord);
}
