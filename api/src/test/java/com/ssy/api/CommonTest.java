package com.ssy.api;

import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import com.ssy.api.util.allPictureUtils.repository.PictureRepository;
import com.ssy.api.utils.UUIDUtils;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @ClassName: CommonTest
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 10:31:30 
 * @Version: V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {


    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private PictureRepository pictureRepository;
    @Test
    public  void get(){
        //注意索引名要小写
        CreateIndexRequest request = new CreateIndexRequest("wl");
        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public  void save(){

          PictureDocument pictureDocument =PictureDocument.builder()
                  .createTime("2021-06-02 10:46:29")
                  .id(33).photoName(UUIDUtils.getUUID()).identifyResult("树,植物,木本植物,植被,植物学,自然,绿色的,草,生物群系,春暖花开的季节,盆栽,分支").url("www.baidu.com")
                  .userId( 33).build();
          pictureRepository.save(pictureDocument);


    }



    @Test
    public  void findAll(){
        //注意索引名要小写
        Iterable<PictureDocument> all = pictureRepository.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public  void deleteAll(){
        //注意索引名要小写
//   pictureRepository.deleteAll();
        PictureDocument pictureDocument =PictureDocument.builder()
                .id(43).build();
        pictureRepository.delete(pictureDocument);
        Iterable<PictureDocument> all = pictureRepository.findAll();
        all.forEach(System.out::println);
    }
}
