package com.ssy.api.SQLservice.dto.face;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFaceDto {
    private String faceName;
    private Integer faceId;
    private String url;
    private Float confidence;
    private String photoName;
    private String width;
    private String height;
    private Timestamp createTime;
}
