package com.ssy.api.SQLservice.dao.daoimpl;

import com.querydsl.jpa.impl.JPAQuery;
import com.ssy.api.SQLservice.dao.UserLikeEntityDao;
import com.ssy.api.SQLservice.entity.QUserLike;
import com.ssy.api.SQLservice.entity.UserLike;
import com.ssy.api.service.BaseService;

import java.util.List;

/**
 * @ClassName: UserLikeEntityDaoImpl
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/01 09:17:55 
 * @Version: V1.0
 **/
public class UserLikeEntityDaoImpl extends BaseService implements UserLikeEntityDao {
    @Override
    public List<UserLike> findAllUser() {
        QUserLike qUserLike =QUserLike.userLike;
        JPAQuery<UserLike> userLikeJPAQuery = queryFactory.select(qUserLike).from(qUserLike);
        List<UserLike> fetch = userLikeJPAQuery.fetch();
        return  fetch;

    }
}
