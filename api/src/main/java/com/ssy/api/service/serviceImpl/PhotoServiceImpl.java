package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.entity.UdRecord;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.SQLservice.repository.UdRecordRepository;
import com.ssy.api.SQLservice.dto.face.AddFaceDto;
import com.ssy.api.SQLservice.dto.face.FaceDetectResult;
import com.ssy.api.SQLservice.dto.face.FaceRectangle;
import com.ssy.api.SQLservice.entity.Face;
import com.ssy.api.SQLservice.repository.FaceRepository;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.FaceService;
import com.ssy.api.service.PhotoService;
import com.ssy.api.util.FaceHandlerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoRepository photoRepository;
    @Resource
    private UdRecordRepository recordRepository;

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
    public RestResult saveAll(List<PhotoDto> photoDtos) {
        List<Photo> photos = photoRepository.saveAll(photoDtos.stream().map(photo ->
                Photo.builder()
                        .userId(photo.getUserId())
                        .tagId(photo.getTag_id())
                        .url(photo.getUrl())
                        .latitudeRef(photo.getLatitudeRef())
                        .longitudeRef(photo.getLongitudeRef())
                        .photoSize(photo.getSize())
                        .height(photo.getHeight())
                        .width(photo.getWidth())
                        .photoName(photo.getPhotoName())
                        .latitude(photo.getLatitude())
                        .longitude(photo.getLongitude())
                        .isUpload(1)
                        .isDelete(0)
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .updateTime(new Timestamp(System.currentTimeMillis()))
                        .build()
        ).collect(Collectors.toList()));
        photos.forEach(photo -> {
            List<FaceDetectResult> faceDetectResults;
            try {
                Future<List<FaceDetectResult>> listFuture = faceService.faceDetect(photo.getUrl());
                if (listFuture != null){
                    faceDetectResults =  listFuture.get();
                    if (faceDetectResults != null) {
                        faceRepository.saveAll(faceDetectResults.stream().map(face -> {
                            FaceRectangle faceRectangle = face.getFaceRectangle();
                            String s = faceRectangle.getUpperLeftX() + "," +
                                    faceRectangle.getUpperLeftY() + "," +
                                    faceRectangle.getLowerRightX() + "," +
                                    faceRectangle.getLowerRightY();
                            // faceId设置有问题，需要探测到人脸之后，先添加到人脸大库，在拿返回id设置成faceId 目前名称是默认
                            AddFaceDto addFaceDto = faceService.faceAdd(FaceHandlerUtil.FACE_STORE_ALL,
                                    ParameterConstant.FastDFSPrefix + face.getSubImage()
                                    , "image");
                            return Face.builder()
                                    .faceId(addFaceDto.getFaceId())
                                    .photoId(photo.getId())
                                    .url(ParameterConstant.FastDFSPrefix + face.getSubImage())
                                    .faceRectangle(s)
                                    .confidence((double) face.getFaceScore())
                                    .createTime(new Timestamp(System.currentTimeMillis()))
                                    .updateTime(new Timestamp(System.currentTimeMillis()))
                                    .build();
                        }).collect(Collectors.toList()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
            photo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            return photo;
        }).collect(Collectors.toList()));
        return new RestResultBuilder<>().success("成功");
    }

    @Transactional
    @Override
    public RestResult batchUploadPicture(List<PhotoDto> photos) {
        List<Photo> collect = new ArrayList<>(10);
        List<UdRecord> records = new ArrayList<>();
        photos.forEach(photo -> {
            collect.add(
                    Photo.builder()
                            .userId(photo.getUserId())
                            .tagId(photo.getTag_id())
                            .url(photo.getUrl())
                            .latitudeRef(photo.getLatitudeRef())
                            .longitudeRef(photo.getLongitudeRef())
                            .photoSize(photo.getSize())
                            .height(photo.getHeight())
                            .width(photo.getWidth())
                            .photoName(photo.getPhotoName())
                            .latitude(photo.getLatitude())
                            .longitude(photo.getLongitude())
                            .isUpload(1)
                            .isDelete(0)
                            .createTime(new Timestamp(System.currentTimeMillis()))
                            .updateTime(new Timestamp(System.currentTimeMillis()))
                            .build());
        });
        List<Photo> photoList = photoRepository.saveAll(collect);
        photoList.forEach(photo -> {
            UdRecord udRecord = UdRecord.builder()
                    .type(0)
                    .status(1)
                    .photoId(String.valueOf(photo.getId()))
                    .userId(photo.getUserId())
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .isDelete(0).build();
            records.add(udRecord);
            System.out.println("记录的内容是: ");
            System.out.println(udRecord.getPhotoId());
        });
        recordRepository.saveAll(records);
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < photoList.size(); i++) {
            if(i == 0 && i != photoList.size() - 1) {
                ids = new StringBuilder(photoList.get(i).getId() + ",");
            } else if( i == photoList.size() - 1) {
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
    public RestResult findInTrashcan(PhotoDto photoDto) {

        return null;
    }
}
