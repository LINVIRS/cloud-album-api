package com.ssy.api.SQLservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDto extends BaseDto {
    // 插入属性
    private Integer userId;
    private String url;
    private String tag_id;
    private String longitude;
    private String latitude;
    private Integer isUpload;
    private String longitudeRef;
    private String latitudeRef;
    private String height;
    private String width;
    private String photoName;
    private String size;
}
