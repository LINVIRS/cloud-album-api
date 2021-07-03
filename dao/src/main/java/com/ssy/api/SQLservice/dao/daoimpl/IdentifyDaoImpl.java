package com.ssy.api.SQLservice.dao.daoimpl;

import com.querydsl.core.Tuple;
import com.ssy.api.SQLservice.dao.IdentifyDao;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.QIdentify;
import com.ssy.api.SQLservice.entity.QPhoto;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

import java.util.List;

/**
 * @ClassName: IdentifyDaoImpl
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 16:58:07 
 * @Version: V1.0
 **/
public class IdentifyDaoImpl extends BaseService implements IdentifyDao {


    @Override
    public List<Photo> searchUserPicture(Integer userId, String type,Integer pageSize,Integer pageIndex) {
        QPhoto qPhoto =QPhoto.photo;
        QIdentify qIdentify =QIdentify.identify;
        return queryFactory.select(qPhoto).from(qIdentify).leftJoin(qPhoto)
                .on(qIdentify.photoId.eq(qPhoto.id)).where(qPhoto.isDelete.eq(CommonConstant.DELFlag)
                .and(qIdentify.type.like(type)
                ).and(qIdentify.userId.eq(userId))).orderBy(qPhoto.createTime.desc()).limit(pageSize).offset(pageIndex*pageSize).fetch();
    }

    @Override
    public List<String> findPictureType(Integer userId) {
        QIdentify qIdentify =QIdentify.identify;
        return  queryFactory.select(qIdentify.type).from(qIdentify)
                .where(qIdentify.userId.eq(userId))
                .groupBy(qIdentify.type)
                .fetch();
    }

    @Override
    public Tuple findPictureCover(Integer userId, String type) {
       QIdentify qIdentify =QIdentify.identify;
       QPhoto qPhoto =QPhoto.photo;
       return  queryFactory.select(qPhoto.url,qPhoto.createTime).from(qIdentify).leftJoin(qPhoto)
               .on(qIdentify.photoId.eq(qPhoto.id))
               .where(qIdentify.isDelete.eq(CommonConstant.DELFlag)
               .and(qIdentify.userId.eq(userId)
               .and(qIdentify.type.eq(type)))).orderBy(qPhoto.createTime.desc()).fetchFirst();

    }

    @Override
    public List<Integer> findPictureId(Integer userId, String type) {
        QIdentify qIdentify =QIdentify.identify;
        return queryFactory.select(qIdentify.photoId).from(qIdentify)
                .where(qIdentify.userId.eq(userId).and(qIdentify.type.eq(type)))
                .fetch();
    }
}
