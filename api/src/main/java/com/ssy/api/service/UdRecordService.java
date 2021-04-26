package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.UdRecordDto;
import com.ssy.api.result.RestResult;

import java.util.List;

public interface UdRecordService {
    RestResult saveAll(List<UdRecordDto> udRecordDtos);

    /**
     * 查询该用户最近上传记录
     * @param userId
     * @return
     */
    RestResult selectAll(int userId);
}
