package com.ssy.api.SQLservice.dao.daoimpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.ssy.api.SQLservice.dao.UserDao;
import com.ssy.api.SQLservice.dto.UserDataDto;
import com.ssy.api.SQLservice.dto.UserDto;
import com.ssy.api.SQLservice.entity.QUser;
import com.ssy.api.SQLservice.entity.User;
import com.ssy.api.SQLservice.vo.UserVo;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;

/**
 * @ClassName: UserDaoImpl @Description: TODO @Author: WangLinLIN @Date:
 * 2021/04/06 17:59:15  @Version: V1.0
 */
public class UserDaoImpl extends BaseService implements UserDao {

  @Override
  public User userLogin(UserDto userDto) {
    QUser qUser = QUser.user;
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if (userDto.getAccount() != null) {
      booleanBuilder.and(
          qUser.account.eq(userDto.getAccount()).or(qUser.phone.eq(userDto.getAccount())));
    }
    return queryFactory
        .select(qUser)
        .from(qUser)
        .where(booleanBuilder.and(qUser.isDelete.eq(CommonConstant.DELFlag)))
        .fetchOne();
  }

  @Override
  public User findUserByPhone(String phone) {
    QUser qUser = QUser.user;
    return queryFactory
        .select(qUser)
        .from(qUser)
        .where(qUser.phone.eq(phone).and(qUser.isDelete.eq(CommonConstant.DELFlag)))
        .fetchOne();
  }

  @Override
  public User findUserByAccount(String account) {
    QUser qUser = QUser.user;
    return queryFactory
        .select(qUser)
        .from(qUser)
        .where(qUser.account.eq(account).and(qUser.isDelete.eq(CommonConstant.DELFlag)))
        .fetchOne();
  }

  @Override
  public UserVo findUserData(int userId) {
    QUser qUser = QUser.user;
    return queryFactory
        .select(
            Projections.bean(
                UserVo.class,
                qUser.address,
                qUser.avatar,
                qUser.id,
                qUser.nickname,
                qUser.account,
                qUser.level,
                qUser.idCard.as("IDCard"),
                qUser.sex,
                qUser.phone))
        .from(qUser)
        .where(qUser.id.eq(userId).and(qUser.isDelete.eq(CommonConstant.DELFlag)))
        .fetchOne();
  }

  @Override
  public int updateUserData(UserDataDto userDataDto) {
    QUser qUser = QUser.user;
    return Math.toIntExact(
        queryFactory
            .update(qUser)
            .set(qUser.address, userDataDto.getAddress())
            .set(qUser.avatar, userDataDto.getAvatar())
            .set(qUser.nickname, userDataDto.getNickName())
            .set(qUser.sex, userDataDto.getSex())
            .where(qUser.id.eq(userDataDto.getId()))
            .execute());
  }
}
