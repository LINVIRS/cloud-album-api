package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.PhotoService;
import com.ssy.api.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoRepository photoRepository;

    @Override
    @Transactional
    public RestResult findAll(PhotoDto photoDto) {
        return new RestResultBuilder<>().success(photoRepository.findAllPhoto(photoDto));
    }

    @Override
    @Transactional
    public RestResult findById(Integer id) {
        return new RestResultBuilder<>().success(photoRepository.findById(id).get());
    }

    @Override
    @Transactional
    public RestResult saveAll(List<PhotoDto> photoDtos) {
        List<Photo> collect = new ArrayList<>(10);
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 3);
        photoDtos.forEach(photo -> {
            collect.add(Photo.builder()
                    .id((int) snowflakeIdWorker.nextId())
                    .userId(photo.getUserId())
                    .tagId(photo.getTag_id())
                    .url(photo.getUrl())
                    .latitude(photo.getLatitude())
                    .longitude(photo.getLongitude())
                    .isUpload(1)
                    .isDelete(0)
                    .createTime(new Timestamp(System.currentTimeMillis()))
                    .updateTime(new Timestamp(System.currentTimeMillis()))
                    .build());
        });
        photoRepository.saveAll(collect);
        return new RestResultBuilder<>().success("成功");
    }
}
