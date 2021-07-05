package com.ssy.api.SQLservice.vo;

import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * ShareAlbumVo
 *
 * @author wf
 * @date 2021/4/30 14:37
 * @description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareAlbumVo {
    private Integer id;
    private String keyWord;
    private User creator;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String cover;
    private String name;
    private Integer photoNumber;
}
