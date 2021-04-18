package com.ssy.api.SQLservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AlbumDto
 *
 * @author wf
 * @date 2021/4/7 13:33
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {
    private Integer id;
    /**
     * 用户id，即相册创建者id
     */
    private Integer userId;
    /**
     * 相册类型
     */
    private Integer type;
    /**
     * 相册名
     */
    private String name;
    /**
     * 封面
     */
    private String cover;
    /**
     * 照片id
     */
    private String photoId;
    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 图片数量
     */
    private String photoNumber;
}

