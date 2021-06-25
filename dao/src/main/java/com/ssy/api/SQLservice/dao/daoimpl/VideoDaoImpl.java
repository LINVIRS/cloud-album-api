package com.ssy.api.SQLservice.dao.daoimpl;

import com.querydsl.jpa.impl.JPAQuery;
import com.ssy.api.SQLservice.dao.VideoDao;
import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.entity.QVideo;
import com.ssy.api.SQLservice.entity.Video;
import com.ssy.api.service.BaseService;

import java.util.List;

public class VideoDaoImpl extends BaseService implements VideoDao {
    @Override
    public List<Video> findAllVideo(PageDto pageDto) {
        QVideo qVideo = QVideo.video;
        JPAQuery<Video> videoJPAQuery = queryFactory.select(qVideo).from(qVideo)
                .offset((long) pageDto.getPageIndex() * pageDto.getPageSize())
                .limit(pageDto.getPageSize())
                .orderBy(qVideo.createTime.desc());
        return videoJPAQuery.fetch();
    }
}
