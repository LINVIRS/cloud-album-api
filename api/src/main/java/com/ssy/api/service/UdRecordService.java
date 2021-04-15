package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.UdRecordDto;
import com.ssy.api.result.RestResult;

import java.util.List;

public interface UdRecordService {
    RestResult saveAll(List<UdRecordDto> udRecordDtos);
}
