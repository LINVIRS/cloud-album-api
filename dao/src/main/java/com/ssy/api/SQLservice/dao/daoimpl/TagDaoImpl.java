package com.ssy.api.SQLservice.dao.daoimpl;

import com.querydsl.core.types.Projections;
import com.ssy.api.SQLservice.dao.TagDao;
import com.ssy.api.SQLservice.entity.QTag;
import com.ssy.api.SQLservice.vo.TagVo;
import com.ssy.api.enums.CommonConstant;
import com.ssy.api.service.BaseService;
import org.hibernate.criterion.Projection;

import javax.transaction.Transactional;
import java.util.List;

public class TagDaoImpl  extends BaseService implements TagDao {
    @Override
    @Transactional(rollbackOn = Exception.class)
    public long deleteTag(int tagId) {
        QTag qTag =QTag.tag;
        return queryFactory.update(qTag)
                .set(qTag.isDelete,CommonConstant.DELETE_DELFlag)
                .where(qTag.id.eq(tagId))
                .execute();
    }

    @Override
    public List<TagVo> findTagList(int userId) {
        QTag qTag =QTag.tag;
        return queryFactory.select(Projections.bean(
                TagVo.class,
                 qTag.id,
                qTag.userId,
                qTag.name,
                qTag.description,
                qTag.createTime.as("createTime")
        )).from(qTag).where(qTag.userId.in(userId,0).and(qTag.isDelete.eq(CommonConstant.DELFlag)))
                .fetch();
    }
}
