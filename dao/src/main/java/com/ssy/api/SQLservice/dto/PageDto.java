package com.ssy.api.SQLservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PageDto
 *
 * @author wf
 * @date 2021/4/7 15:12
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    /**
     * 第几页
     */
    private int pageIndex;
    /**
     * 页数
     */
    private int pageSize;

    private Integer userId;
}

