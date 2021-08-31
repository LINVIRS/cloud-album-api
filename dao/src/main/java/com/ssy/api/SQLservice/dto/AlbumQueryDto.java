package com.ssy.api.SQLservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * QueryDto
 *
 * @author wf
 * @date 2021/4/7 14:13
 * @description
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AlbumQueryDto {
    /**
     * 排序字段
     */
    private String sortStr;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 分页页数
     */
    private Integer pageIndex;
    /**
     * 每页页数
     */
    private Integer pageSize;
}

