package com.ssy.api.SQLservice.dao.daoimpl;

import com.ssy.api.SQLservice.dao.ShareAlbumDao;
import com.ssy.api.SQLservice.entity.QShareAlbum;
import com.ssy.api.SQLservice.entity.ShareAlbum;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

import java.util.List;

/**
 * ShareAlbumDaoImpl
 *
 * @author wf
 * @date 2021/4/30 14:51
 * @description
 */
public class ShareAlbumDaoImpl extends BaseService implements ShareAlbumDao {

    @Override
    public List<ShareAlbum> selectAll(int userId) {
        QShareAlbum qShareAlbum = QShareAlbum.shareAlbum;
        return queryFactory.select(qShareAlbum).from(qShareAlbum)
                .where(qShareAlbum.creatorId.eq(userId)
                .or(qShareAlbum.joinUserId.contains(String.valueOf(userId)))
                )
                .where((qShareAlbum.isDelete.eq(CommonConstant.DELFlag)))
                .fetch();
    }

    @Override
    public ShareAlbum getOneByKeyWord(String keyWord) {
        QShareAlbum qShareAlbum = QShareAlbum.shareAlbum;
        return queryFactory.select(qShareAlbum).from(qShareAlbum)
                .where(qShareAlbum.keyWord.eq(keyWord)
                .and(qShareAlbum.isDelete.eq(CommonConstant.DELFlag)))
                .fetchOne();
    }

    @Override
    public ShareAlbum searchOneByCreatorIdAndAlbumId(int userId, int albumId) {
        QShareAlbum qShareAlbum = QShareAlbum.shareAlbum;
        return queryFactory.select(qShareAlbum).from(qShareAlbum)
                .where(qShareAlbum.albumId.eq(albumId).and(qShareAlbum.creatorId.eq(userId))
                .and(qShareAlbum.isDelete.eq(CommonConstant.DELFlag)))
                .fetchOne();
    }

    @Override
    public ShareAlbum searchOneByJoinUserIdAndAlbumId(int joinUserId, String keyWord) {
        QShareAlbum qShareAlbum = QShareAlbum.shareAlbum;
        return queryFactory.select(qShareAlbum).from(qShareAlbum)
                .where(qShareAlbum.keyWord.eq(keyWord).and(qShareAlbum.joinUserId.contains(String.valueOf(joinUserId)))
                        .and(qShareAlbum.isDelete.eq(CommonConstant.DELFlag)))
                .fetchOne();
    }
}
