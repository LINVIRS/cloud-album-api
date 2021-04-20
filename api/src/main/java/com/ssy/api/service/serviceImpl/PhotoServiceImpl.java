package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.dto.face.FaceDetectResult;
import com.ssy.api.SQLservice.entity.Face;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.repository.FaceRepository;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.FaceService;
import com.ssy.api.service.PhotoService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoRepository photoRepository;

    @Resource
    private FaceService faceService;

    @Resource
    private FaceRepository faceRepository;

    @Override
    @Transactional
    public RestResult findById(Integer id) {
        return new RestResultBuilder<>().success(photoRepository.findById(id).get());
    }

    @Override
    @Transactional
    @Async
    public Future<RestResult> saveAll(List<PhotoDto> photoDtos) {
        List<Photo> photos = photoRepository.saveAll(photoDtos.stream().map(photo ->
                Photo.builder()
                        .userId(photo.getUserId())
                        .tagId(photo.getTag_id())
                        .url(photo.getUrl())
                        .latitude(photo.getLatitude())
                        .longitude(photo.getLongitude())
                        .isUpload(1)
                        .isDelete(0)
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .updateTime(new Timestamp(System.currentTimeMillis()))
                        .build()
        ).collect(Collectors.toList()));
        photos.forEach(photo -> {
            List<FaceDetectResult> faceDetectResults = faceService.faceDetect(photo.getUrl());
            if (faceDetectResults != null) {
                faceRepository.saveAll(faceDetectResults.stream().map(face -> Face.builder()
                        .faceId(face.getFaceId())
                        .photoId(photo.getId())
                        .url(ParameterConstant.FastDFSPrefix + face.getSubImage())
                        .confidence((double) face.getFaceScore())
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .updateTime(new Timestamp(System.currentTimeMillis()))
                        .build()).collect(Collectors.toList()));
            }
        });
        return new AsyncResult<>(new RestResultBuilder<>().success("成功"));
    }


    public RestResult findAll(PhotoDto photoDto) {
        return new RestResultBuilder<>().success(photoRepository.findAllPhoto(photoDto));

    }

    @Transactional
    @Override
    public RestResult batchUploadPicture(List<PhotoDto> photos) {
        List<Photo> collect = new ArrayList<>(10);
        photos.forEach(photo -> {
            collect.add(Photo.builder()
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
        List<Photo> photoList = photoRepository.saveAll(collect);
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < photoList.size(); i++) {
            if (i == 0 && i != photoList.size() - 1) {
                ids = new StringBuilder(photoList.get(i).getId() + ",");
            } else if (i == photoList.size() - 1) {
                ids.append(photoList.get(i).getId());
            } else {
                ids.append(photoList.get(i).getId()).append(",");
            }
        }
        System.out.println(photoList);
        return new RestResultBuilder<>().success(ids);
    }

    @Override
    @Transactional
    public RestResult delete(List<Integer> ids) {
        photoRepository.saveAll(ids.stream().map(i -> {
            Photo photo = photoRepository.findById(i).get();
            photo.setIsDelete(1);
            photo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            return photo;
        }).collect(Collectors.toList()));
        return new RestResultBuilder<>().success("成功");
    }

    @Override
    @Transactional
    public RestResult findInTrashcan(PhotoDto photoDto) {

        return null;
    }
}
