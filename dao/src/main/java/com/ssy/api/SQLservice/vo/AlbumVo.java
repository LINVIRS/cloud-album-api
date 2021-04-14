package com.ssy.api.SQLservice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * AlbumVo
 *
 * @author wf
 * @date 2021/4/7 16:21
 * @description
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumVo {
    private int id;
    private Integer userId;
    private Integer type;
    private Integer createType;
    private String photoId;
    private String name;
    private String photoNumber;
    private Integer totalCapacity;
    private Integer tagId;
    private Timestamp createTime;
    private Timestamp updateTime;
}
