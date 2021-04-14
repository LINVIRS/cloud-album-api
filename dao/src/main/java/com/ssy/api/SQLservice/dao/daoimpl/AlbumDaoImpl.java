package com.ssy.api.SQLservice.dao.daoimpl;

import com.ssy.api.SQLservice.dao.AlbumDao;
import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.dto.QueryDto;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.QAlbums;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

import java.util.List;

/**
 * AlbumDaoImpl
 *
 * @author wf
 * @date 2021/4/7 13:46
 * @description
 */
public class AlbumDaoImpl extends BaseService implements AlbumDao {

    @Override
    public List<Albums> getAllAlbumsByUserId(QueryDto queryDto, int UserId, String sortStr, PageDto pageDto) {
        QAlbums qAlbums = QAlbums.albums;
        //查询条件暂留保存
        /*String queryStr = "";
        switch (queryDto.getSortStr()) {
            case "name" :
                queryStr = "name";
                break;
            case "createTime" :
                queryStr = "createTime";
                break;
            case "updateTime" :
                queryStr = "updateTime";
                break;
            default:
                break;
        }*/
        return queryFactory.select(qAlbums).from(qAlbums)
                .where(qAlbums.userId.eq(qAlbums.userId).and(qAlbums.isDelete.eq(CommonConstant.DELETE_DELFlag)))
                .limit(pageDto.getPageSize()).offset((long) pageDto.getPageSize() * pageDto.getPageIndex())
                .fetch();
    }

    @Override
    public List<Photo> getAlbumDetailById(int albumId) {
        QAlbums qAlbums = QAlbums.albums;
        return null;
    }

    @Override
    public int updateAlbumById(AlbumDto albumDto, int albumId) {
        QAlbums qAlbums = QAlbums.albums;
        return Math.toIntExact(queryFactory.update(qAlbums)
                .set(qAlbums.name, albumDto.getName())
                .set(qAlbums.isDelete, albumDto.getIsDelete())
                .set(qAlbums.photoId, albumDto.getPhotoId())
                .set(qAlbums.type, albumDto.getType())
                .where(qAlbums.id.eq(albumId))
                .execute());
    }
}
