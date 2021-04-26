package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.UdRecordDto;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.UdRecord;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.SQLservice.repository.UdRecordRepository;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.UdRecordService;
import com.ssy.api.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UdRecordServiceImpl implements UdRecordService {
    @Resource
    private UdRecordRepository udRecordRepository;
    @Resource
    private PhotoRepository photoRepository;

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

    @Override
    public RestResult selectAll(int userId) {
        List<Photo> photoList = new ArrayList<>();
        List<UdRecord> records = udRecordRepository.selectAll(userId);
        List<UdRecord> recordList = new ArrayList<>();
        records.forEach(record -> {
            Photo photo = photoRepository.findDetailById(Integer.parseInt(record.getPhotoId()));
            if(photo != null) {
                photoList.add(photo);
            }
        });
        Map<String, Object> recordInfo = new HashMap<>();
        Map<String, List<Photo>> resultMap = new HashMap<>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        int photoNumber = 0;
        for (Photo photo : photoList) {
            String time = "";
            for(UdRecord record : records) {
                if(record.getPhotoId().equals(String.valueOf(photo.getId()))) {
                     time = record.getUpdateTime().toString().substring(0, 11);
                    recordList.add(record);
                     break;
                }
            }
            if(resultMap.containsKey(time)) {
                resultMap.get(time).add(photo);
            } else {
                List<Photo> photoList1 = new ArrayList<Photo>();
                photoList1.add(photo);
                resultMap.put(time, photoList1);
            }
        }
        for (Map.Entry<String, List<Photo>> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            List<Photo> list = entry.getValue();
            List<Photo> photoList1 = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            for (Photo photo : list) {
                photoList1.add(Photo.builder().id(photo.getId())
                        .url(photo.getUrl())
                        .isUpload(photo.getIsUpload())
                        .tagId(photo.getTagId())
                        .longitude(photo.getLongitude())
                        .latitude(photo.getLatitude())
                        .userId(photo.getUserId())
                        .createTime(photo.getCreateTime())
                        .updateTime(photo.getUpdateTime()).build());
            }
            map.put("date", key);
            map.put("list", photoList1);
            resultList.add(map);
        }
        recordInfo.put("list", resultList);
        recordInfo.put("photoNumber", photoNumber);
        return new RestResultBuilder<>().success(recordInfo);
    }
}
