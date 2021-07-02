package com.ssy.api.util.allPictureUtils.service;

import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PictureService
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 09:33:03 
 * @Version: V1.0
 **/

public interface PictureService {

    /**
     * 高级搜索
     * @param content
     * @param userId
     * @param pageSize
     * @param pageIndex
     * @return
     */
    List<PictureDocument> search(String content,Integer userId,Integer pageSize, Integer pageIndex) ;

    /**
     * 中文、拼音混合搜索
     *
     * @param content the content
     * @return dis max query builder
     */
    public DisMaxQueryBuilder structureQuery(String content);

}
