package com.ssy.api.util.allPictureUtils.service.impl;

import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import com.ssy.api.util.allPictureUtils.service.PictureService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
*@ClassName: PictureServiceImpl
*@Description: TODO
@Author: WangLinLIN
@Date: 2021/07/01 09:33:46 
@Version: V1.0
**/
@Service
public class PictureServiceImpl implements PictureService {
    @Resource
//    private PictureRepository pictureRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Override
    public List<PictureDocument> search(String content,Integer userId,Integer pageSize, Integer pageIndex) {
        //使用中文拼音混合搜索，取分数最高的，具体评分规则可参照：
        //  https://blog.csdn.net/paditang/article/details/79098830
        QueryBuilder shouldQuery= QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("photoName",content).boost(1.0f))
                .should(QueryBuilders.matchQuery("identifyResult",content).boost(2.0f))
                //matchPhraseQuery 连续查询
                .should(QueryBuilders.matchPhraseQuery("identifyResult.pinyin",content).boost(1.5f));
        QueryBuilder queryBuilder= QueryBuilders.boolQuery().must(
                shouldQuery
           ).must(QueryBuilders.termQuery("userId",userId));
        Pageable page = PageRequest.of(pageIndex,pageSize);
        NativeSearchQuery searchQuery=new NativeSearchQueryBuilder()
                .withQuery(queryBuilder).withPageable(page)
                .build();
        SearchHits<PictureDocument> search = elasticsearchRestTemplate.search(searchQuery, PictureDocument.class);
        List<PictureDocument>list =new ArrayList<>();
        if (search.hasSearchHits()==false){
            return list;
        }
        List<SearchHit<PictureDocument>> searchHits = search.getSearchHits();
        for (   SearchHit<PictureDocument> searchHits1:searchHits){
            list.add(searchHits1.getContent());
        }
        return list;
    }

    @Override
    public DisMaxQueryBuilder structureQuery(String content) {
        //使用dis_max直接取多个query中，分数最高的那一个query的分数即可
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
        //boost 设置权重,只搜索匹配name和disrector字段
        QueryBuilder ikNameQuery = QueryBuilders.matchQuery("photoName", content).boost(2f);
        QueryBuilder pinyinNameQuery = QueryBuilders.matchQuery("photoName.pinyin", content);
        QueryBuilder ikDirectorQuery = QueryBuilders.matchQuery("identifyResult", content).boost(2f);
        disMaxQueryBuilder.add(ikNameQuery);
        disMaxQueryBuilder.add(pinyinNameQuery);
        disMaxQueryBuilder.add(ikDirectorQuery);
        return disMaxQueryBuilder;
    }
}
