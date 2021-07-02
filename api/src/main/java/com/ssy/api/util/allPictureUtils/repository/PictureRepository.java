package com.ssy.api.util.allPictureUtils.repository;

import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PictureRepository
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 09:46:20 
 * @Version: V1.0
 **/

public interface PictureRepository  extends  ElasticsearchRepository<PictureDocument,Integer>  {


}
