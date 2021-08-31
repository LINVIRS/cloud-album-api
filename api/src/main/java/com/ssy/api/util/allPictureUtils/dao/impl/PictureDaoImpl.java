package com.ssy.api.util.allPictureUtils.dao.impl;

import com.ssy.api.util.allPictureUtils.dao.PictureDao;
import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * @ClassName: PictureDaoImpl
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 14:33:18 
 * @Version: V1.0
 **/
public class PictureDaoImpl implements PictureDao {



}
