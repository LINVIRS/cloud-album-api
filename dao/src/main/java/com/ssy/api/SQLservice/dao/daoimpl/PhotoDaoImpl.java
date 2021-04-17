package com.ssy.api.SQLservice.dao.daoimpl;

import com.querydsl.jpa.impl.JPAQuery;
import com.ssy.api.SQLservice.dao.PhotoDao;
import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.QPhoto;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

import java.util.List;

public class PhotoDaoImpl extends BaseService implements PhotoDao {
    @Override
    public List<Photo> findAllPhoto(PhotoDto photoDto) {
        QPhoto qPhoto = QPhoto.photo;
        JPAQuery<Photo> photoJPAQuery = queryFactory.select(qPhoto).from(qPhoto)
                .offset((long) photoDto.getPage() * photoDto.getPageSize())
                .limit(photoDto.getPageSize())
                .orderBy(qPhoto.createTime.desc());

        return photoJPAQuery.fetch();
    }


    @Override
    public Photo findDetailById(Integer id) {
        QPhoto qPhoto = QPhoto.photo;
        JPAQuery<Photo> photoJPAQuery = queryFactory.select(qPhoto).from(qPhoto)
                .where(qPhoto.id.eq(id).and(qPhoto.isDelete.eq(CommonConstant.DELFlag)));
        return photoJPAQuery.fetchOne();
    }
}
