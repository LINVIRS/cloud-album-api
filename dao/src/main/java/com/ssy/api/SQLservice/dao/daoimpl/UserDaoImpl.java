package com.ssy.api.SQLservice.dao.daoimpl;

import com.ssy.api.SQLservice.dao.UserDao;
import com.ssy.api.SQLservice.dto.UserDto;
import com.ssy.api.SQLservice.entity.QUser;
import com.ssy.api.SQLservice.entity.User;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

/**
 * @ClassName: UserDaoImpl
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/06 17:59:15 
 * @Version: V1.0
 **/
public class UserDaoImpl extends BaseService implements UserDao {


    @Override
    public User userLogin(UserDto userDto) {
        QUser qUser =QUser.user;
        return queryFactory.select(qUser).from(qUser)
                .where(qUser.phone.eq(userDto.getPhoneNumber())
                .or(qUser.account.eq(userDto.getAccount())
                        .and(qUser.isDelete.eq(CommonConstant.DELFlag))))
                .fetchOne();

    }

    @Override
    public User findUserByPhone(String phone) {
        QUser qUser =QUser.user;
        return queryFactory.select(qUser).from(qUser)
                .where(qUser.phone.eq(phone).and(qUser.isDelete.eq(CommonConstant.DELFlag)))
                .fetchOne();
    }

    @Override
    public User findUserByAccount(String account) {
        QUser qUser =QUser.user;
        return queryFactory.select(qUser).from(qUser)
                .where(qUser.account.eq(account).and(qUser.isDelete.eq(CommonConstant.DELFlag)))
                .fetchOne();
    }
}
