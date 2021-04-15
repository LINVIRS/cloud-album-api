package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.UdRecordDto;
import com.ssy.api.SQLservice.entity.UdRecord;
import com.ssy.api.SQLservice.repository.UdRecordRepository;
import com.ssy.api.result.RestResult;
import com.ssy.api.service.UdRecordService;
import com.ssy.api.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UdRecordServiceImpl implements UdRecordService {
    @Resource
    private UdRecordRepository udRecordRepository;

    @Override
    @Transactional
    public RestResult saveAll(List<UdRecordDto> udRecordDtos) {
        List<UdRecord> collect = new ArrayList<>(10);
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 4);
        udRecordDtos.forEach(udRecord -> {
            collect.add(UdRecord.builder()
                    .id((int) snowflakeIdWorker.nextId())
                    .userId(udRecord.getUserId())
                    .type(udRecord.getType())
                    .photoId(udRecord.getPhotoId())
                    .status(udRecord.getStatus())
                    .isDelete(0)
                    .createTime(new Timestamp(System.currentTimeMillis()))
                    .updateTime(new Timestamp(System.currentTimeMillis()))
                    .build());
        });
        udRecordRepository.saveAll(collect);
        return null;
    }
}
