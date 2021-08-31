package com.ssy.api.util.allPictureUtils.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @ClassName: PictureDocument
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/06/30 15:31:45 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "picture" )
@Setting(settingPath = "/json/picture_setting.json")
@Mapping(mappingPath = "/json/picture_mapping.json")
public class PictureDocument {
    private int id;
    private String url;
    private Integer userId;
    private String photoName;
    private String identifyResult;
    private String createTime;
}
