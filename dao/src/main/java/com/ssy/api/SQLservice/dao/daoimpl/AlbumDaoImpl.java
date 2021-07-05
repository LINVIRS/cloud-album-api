package com.ssy.api.SQLservice.dao.daoimpl;

import com.ssy.api.SQLservice.dao.AlbumDao;
import com.ssy.api.SQLservice.dto.AlbumDto;
import com.ssy.api.SQLservice.dto.AlbumQueryDto;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.entity.QAlbums;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;
import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.Queue;

/**
 * AlbumDaoImpl
 *
 * @author wf
 * @date 2021/4/7 13:46
 * @description
 */
public class AlbumDaoImpl extends BaseService implements AlbumDao {

    @Override
    public List<Albums> getAllAlbumsByUserId(AlbumQueryDto queryDto) {
        QAlbums qAlbums = QAlbums.albums;
        System.out.println("排序字段是: " + queryDto.getSortStr());
        //查询条件暂留保存
        switch (queryDto.getSortStr()) {
            case "name":
                //根据名称排序
                return queryFactory.select(qAlbums).from(qAlbums)
                        .where(qAlbums.userId.eq(queryDto.getUserId()).and(qAlbums.createType.eq(0)).and(qAlbums.isDelete.eq(CommonConstant.DELFlag)))
                        .limit(queryDto.getPageSize()).offset((long) queryDto.getPageSize() * queryDto.getPageIndex())
                        .orderBy(qAlbums.name.desc())

                        .fetch();
            case "createTime":
                //根据创建时间排序
                return queryFactory.select(qAlbums).from(qAlbums)
                        .where(qAlbums.userId.eq(queryDto.getUserId()).and(qAlbums.createType.eq(0)).and(qAlbums.isDelete.eq(CommonConstant.DELFlag)))
                        .limit(queryDto.getPageSize()).offset((long) queryDto.getPageSize() * queryDto.getPageIndex())
                        .orderBy(qAlbums.createTime.desc())
                        .fetch();
            case "updateTime":
                return queryFactory.select(qAlbums).from(qAlbums)
                        .where(qAlbums.userId.eq(queryDto.getUserId()).and(qAlbums.createType.eq(0)).and(qAlbums.isDelete.eq(CommonConstant.DELFlag)))
                        .limit(queryDto.getPageSize()).offset((long) queryDto.getPageSize() * queryDto.getPageIndex())
                        .orderBy(qAlbums.updateTime.desc())
                        .fetch();
            default:
                break;
        }
        //默认创建时间排序
        return queryFactory.select(qAlbums).from(qAlbums)
                .where(qAlbums.userId.eq(queryDto.getUserId()).and(qAlbums.createType.eq(0)).and(qAlbums.isDelete.eq(CommonConstant.DELFlag)))
                .limit(queryDto.getPageSize()).offset((long) queryDto.getPageSize() * queryDto.getPageIndex())
                .orderBy(qAlbums.createTime.desc())
                .fetch();

    }

    @Override
    public List<Albums> getAllAlbumsByIdentify(Integer userId) {
        QAlbums qAlbums = QAlbums.albums;
        return queryFactory.select(qAlbums).from(qAlbums)
                .where(qAlbums.userId.eq(userId).and(qAlbums.createType.eq(1)).and(qAlbums.isDelete.eq(CommonConstant.DELFlag)))
                .orderBy(qAlbums.createTime.desc())
                .fetch();

    }

    @Override
    public Albums getAlbumDetailById(int albumId) {
        QAlbums qAlbums = QAlbums.albums;
        return queryFactory.select(qAlbums).from(qAlbums)
                .where(qAlbums.id.eq(albumId).and(qAlbums.isDelete.eq(CommonConstant.DELFlag)))
                .fetchOne();
    }

    @Override
    public int updateAlbumById(AlbumDto albumDto, int albumId) {
        QAlbums qAlbums = QAlbums.albums;
        return Math.toIntExact(queryFactory.update(qAlbums)
                .set(qAlbums.name, albumDto.getName())
                .set(qAlbums.isDelete, albumDto.getIsDelete())
                .set(qAlbums.photoId, albumDto.getPhotoId())
                .set(qAlbums.type, albumDto.getType())
                .set(qAlbums.photoNumber, albumDto.getPhotoNumber())
                .where(qAlbums.id.eq(albumId))
                .execute());
    }

    @Override
    public int updateAlbum(int albumId, String cover, String photoId, String num) {
        QAlbums qAlbums = QAlbums.albums;
        return Math.toIntExact(queryFactory.update(qAlbums)
                .set(qAlbums.photoId, photoId)
                .set(qAlbums.cover, cover)
                .set(qAlbums.photoNumber, num)
                .where(qAlbums.id.eq(albumId))
                .execute());
    }
}
