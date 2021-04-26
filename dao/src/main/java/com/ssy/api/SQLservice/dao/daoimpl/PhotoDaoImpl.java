package com.ssy.api.SQLservice.dao.daoimpl;

import com.querydsl.jpa.impl.JPAQuery;
import com.ssy.api.SQLservice.dao.PhotoDao;
import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.QPhoto;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wanglin
 */
public class PhotoDaoImpl extends BaseService implements PhotoDao {
    @Override
    public List<Photo> findAllPhoto(PhotoDto photoDto) {
        QPhoto qPhoto = QPhoto.photo;
        JPAQuery<Photo> photoJPAQuery = queryFactory.select(qPhoto).from(qPhoto)
                .offset((long) photoDto.getPage() * photoDto.getPageSize())
                .limit(photoDto.getPageSize())
                .where(qPhoto.isDelete.eq(0))
                .orderBy(qPhoto.createTime.desc());
        return photoJPAQuery.fetch();
    }

    @Override
    @Transactional
    public List<Photo> findInTrashcan(PhotoDto photoDto) {
        QPhoto qPhoto = QPhoto.photo;
        JPAQuery<Photo> photoJPAQuery = queryFactory.select(qPhoto).from(qPhoto)
                .offset((long) photoDto.getPage() * photoDto.getPageSize())
                .limit(photoDto.getPageSize())
                // 标记删除
                .where(qPhoto.isDelete.eq(1))
                // 按照修改时间排序
                .orderBy(qPhoto.updateTime.desc());
        return photoJPAQuery.fetch();
    }

    @Override
    public Photo findDetailById(Integer id) {
        QPhoto qPhoto = QPhoto.photo;
        JPAQuery<Photo> photoJPAQuery = queryFactory.select(qPhoto).from(qPhoto)
                .where(qPhoto.id.eq(id).and(qPhoto.isDelete.eq(CommonConstant.DELFlag)));
        return photoJPAQuery.fetchOne();
    }

    @Override
    public List<Photo> findRecentDelPhoto(int userId) {
        QPhoto qPhoto = QPhoto.photo;
        JPAQuery<Photo> photoJPAQuery = queryFactory.select(qPhoto).from(qPhoto)
                .where(qPhoto.isDelete.eq(CommonConstant.DELETE_DELFlag).and(qPhoto.userId.eq(userId))
                        .and(qPhoto.updateTime.between(Timestamp.valueOf(LocalDateTime.now().plusDays(-7)), Timestamp.valueOf(LocalDateTime.now()))))
                        .groupBy(qPhoto.updateTime);
        return photoJPAQuery.fetch();
    }
}
