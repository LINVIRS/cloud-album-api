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
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoRepository photoRepository;

    @Override
    @Transactional
    public RestResult findById(Integer id) {
        return new RestResultBuilder<>().success(photoRepository.findById(id).get());
    }

    @Override
    @Transactional
    public RestResult saveAll(List<PhotoDto> photoDtos) {
        photoRepository.saveAll(photoDtos.stream().map(photo -> Photo.builder()
                .userId(photo.getUserId())
                .tagId(photo.getTag_id())
                .url(photo.getUrl())
                .latitude(photo.getLatitude())
                .longitude(photo.getLongitude())
                .isUpload(1)
                .isDelete(0)
                .createTime(new Timestamp(System.currentTimeMillis()))
                .updateTime(new Timestamp(System.currentTimeMillis()))
                .build()).collect(Collectors.toList()));
        return new RestResultBuilder<>().success("成功");
    }

    public RestResult findAll(PhotoDto photoDto) {
        return new RestResultBuilder<>().success(photoRepository.findAllPhoto(photoDto));
    }

    @Override
    @Transactional
    public RestResult delete(List<Integer> ids) {
        photoRepository.saveAll(ids.stream().map(i -> {
            Photo photo = photoRepository.findById(i).get();
            photo.setIsDelete(1);
            return photo;
        }).collect(Collectors.toList()));
        return new RestResultBuilder<>().success("成功");
    }
}
