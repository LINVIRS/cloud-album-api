package com.ssy.api.SQLservice.dto;

import lombok.Data;

@Data
public class PhotoDto extends BaseDto {
    // 插入属性
    private Integer userId;
    private String url;
    private String tag_id;
    private String longitude;
    private String latitude;
    private Integer isUpload;
}
