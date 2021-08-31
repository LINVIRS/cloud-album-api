package com.ssy.api.service;

import com.ssy.api.SQLservice.vo.ShareAlbumVo;
import com.ssy.api.result.RestResult;

import java.util.List;

/**
 * ShareAlbumService
 *
 * @author wf
 * @date 2021/4/30 15:02
 * @description
 */
public interface ShareAlbumService {

    /**
     * @param  userId 用户id
     * @return List<ShareAlbum>
     */
    List<ShareAlbumVo> selectAllShareAlbum(int userId);

    /**
     * 创建共享相册
     * @param userId 用户id
     * @return ShareAlbumVo
     */
    ShareAlbumVo creatorShareAlbum(int userId, int albumId);

    /**
     * 删除共享相册
     * @param id 相册id
     */
    void deleteShareAlbum(int id);

    /**
     * 加入共享相册
     * @param userId 加入人的id
     * @param keyWord 加入的秘钥
     * @return ShareAlbumVo
     */
    RestResult joinShareAlbum(String keyWord, int joinUserId);
}
