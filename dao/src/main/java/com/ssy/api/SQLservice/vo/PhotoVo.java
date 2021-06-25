package com.ssy.api.SQLservice.vo;

import java.sql.Timestamp;

/**
 * PhotoVo
 *
 * @author wf
 * @date 2021/4/24 14:32
 * @description
 */
public class PhotoVo {
    private int id;
    private String url;
    private Integer isUpload;
    private String tagId;
    private String longitude;
    private String latitude;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer isDelete;
    private String photoName;
    private String photoSize;
    private String latitudeRef;
    private String longitudeRef;
    private String height;
    private String width;
    private String tagName;
}
