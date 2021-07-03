package com.ssy.api.service.serviceImpl;


import com.drew.lang.StringUtil;
import com.ssy.api.SQLservice.dto.PhotoDto;
import com.ssy.api.SQLservice.dto.face.*;
import com.ssy.api.SQLservice.entity.*;
import com.ssy.api.SQLservice.repository.*;
import com.ssy.api.SQLservice.vo.PhotoClassification;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.FaceService;
import com.ssy.api.service.PhotoService;
import com.ssy.api.util.FaceHandlerUtil;
import com.ssy.api.util.FileUtil.fastdfs.FileDfsUtil;
import com.ssy.api.util.allPictureUtils.document.PictureDocument;
import com.ssy.api.util.allPictureUtils.repository.PictureRepository;

import com.ssy.api.utils.Location;
import com.ssy.api.utils.LocationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
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

    @Resource
    private TagRepository tagRepository;

    @Resource
    private FileDfsUtil fileDfsUtil;

    @Resource
    private AlbumRepository albumRepository;

    @Resource
    private FaceStoreRepository faceStoreRepository;
@Resource
private IdentifyRepository identifyRepository;


    @Resource
    private PictureRepository pictureRepository;

    @Override
    @Transactional
    public RestResult findById(Integer id) {
        return new RestResultBuilder<>().success(photoRepository.findById(id).get());
    }

    @Override
    @Transactional
    public RestResult saveAll(List<PhotoDto> photoDtos) {
        List<UdRecord> records = new ArrayList<>();
        // 添加上传的照片
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
                        .time(Timestamp.valueOf(photo.getTime()))
                        .isUpload(1)
                        .isDelete(0)
                        .createTime(new Timestamp(System.currentTimeMillis()))
                        .updateTime(new Timestamp(System.currentTimeMillis()))
                        .build()
        ).collect(Collectors.toList()));
        photos.forEach(photo -> {
            UdRecord udRecord = UdRecord.builder()
                    .type(0)
                    .status(1)
                    .photoId(String.valueOf(photo.getId()))
                    .userId(photo.getUserId())
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .isDelete(0).build();
            records.add(udRecord);
        });
        // 添加到最近上传记录
        recordRepository.saveAll(records);
        // 人脸探测
        photos.forEach(photo -> {
            List<FaceDetectResult> faceDetectResults;
            try {
                Future<List<FaceDetectResult>> listFuture = faceService.faceDetect(photo.getUrl());
                if (listFuture != null) {
                    faceDetectResults = listFuture.get();
                    if (faceDetectResults != null) {
                        // 添加到人脸
                        List<Face> image = faceRepository.saveAll(faceDetectResults.stream().map(face -> {
                            FaceRectangle faceRectangle = face.getFaceRectangle();
                            String s = faceRectangle.getUpperLeftX() + "," +
                                    faceRectangle.getUpperLeftY() + "," +
                                    faceRectangle.getLowerRightX() + "," +
                                    faceRectangle.getLowerRightY();
                            // 添加到人脸大库
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
                        image.forEach(face -> {
                            // 搜索是否有相同的人脸
                            List<SearchFaceDto> searchFaceDtos = faceService.searchFace(face.getUrl(),
                                    FaceHandlerUtil.FACE_STORE_ALL, 10);
                            List<SearchFaceDto> sameFace = new ArrayList<>();
                            searchFaceDtos.forEach(i -> {
                                if (i.getConfidence() > 0.75) {
                                    sameFace.add(i);
                                }
                            });
                            // 判断如果没有相同的脸
                            if (sameFace.size() == 1) {
                                StringBuilder photoId = new StringBuilder();
                                // 返回photoid
                                for (int i = 0; i < photos.size(); i++) {
                                    if (i == 0 && i != photos.size() - 1) {
                                        photoId = new StringBuilder(photos.get(i).getId() + ",");
                                    } else if (i == photos.size() - 1) {
                                        photoId.append(photos.get(i).getId());
                                    } else {
                                        photoId.append(photos.get(i).getId()).append(",");
                                    }
                                }
                                String cover = photos.get(0).getUrl();
                                int userId = photos.get(0).getUserId();
                                FaceStoreDto faceSet = faceService.createFaceSet("new", "新的人脸库");
                                // 创建相册
                                Albums save = albumRepository.save(Albums.builder()
                                        .id(faceSet.getFaceStoreId())
                                        .createType(1)
                                        .cover(cover)
                                        .userId(userId)
                                        .photoId(photoId.toString())
                                        .type(0)
                                        .photoNumber(String.valueOf(photos.size()))
                                        .createTime(new Timestamp(System.currentTimeMillis()))
                                        .updateTime(new Timestamp(System.currentTimeMillis()))
                                        .build());
                                // 创建人脸库
                                faceStoreRepository.save(FaceStore.builder()
                                        .albumId(save.getId())
                                        .faceStoreId(faceSet.getFaceStoreId())
                                        .description(faceSet.getDescription())
                                        .createTime(new Timestamp(System.currentTimeMillis()))
                                        .updateTime(new Timestamp(System.currentTimeMillis()))
                                        .build());
                                // 保存人脸到新的人脸库
                                faceService.faceAdd(faceSet.getFaceStoreId(), face.getUrl(), "image");
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return new RestResultBuilder<>().success(photos);
    }

    @Override
    public RestResult findPhotoByUserId(int userId) {
        List<Photo> photoList = photoRepository.findPhotoByUserId(userId);
        return new RestResultBuilder<>().success(photoList);
    }
    public RestResult findAll(PhotoDto photoDto) {
        if (photoDto.getPageSize() == 0 && photoDto.getPageSize() == null) {
            photoDto.setPageSize(10);
        }
        List<Photo> allPhoto = photoRepository.findAllPhoto(photoDto);
        if (allPhoto.size() == 0) {
            return new RestResultBuilder<>().success();
        }
        Map<String, List<Photo>> collect = allPhoto.stream().collect(
                Collectors.groupingBy(p ->
                        p.getCreateTime().toString().substring(0, 10)
                ));
        List<PhotoClassification> list = new ArrayList<>();
        collect.entrySet().stream().forEach(i -> {
            PhotoClassification photoClassification = new PhotoClassification();
            photoClassification.setPhotoList(i.getValue());
            photoClassification.setYearName(i.getKey());
            list.add(photoClassification);
        });
        return new RestResultBuilder<>().success(list);
    }

    @Override
    public RestResult findAllForPhone(Integer userId) {
        List<Photo> photoForPhone = photoRepository.findPhotoForPhone(userId);
        return new RestResultBuilder<>().success(photoForPhone);
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
            List<FaceDetectResult> faceDetectResults;
            try {
                Future<List<FaceDetectResult>> listFuture = faceService.faceDetect(photo.getUrl());
                if (listFuture != null) {
                    faceDetectResults = listFuture.get();
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
        });
        recordRepository.saveAll(records);
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
        return new RestResultBuilder<>().success(ids);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public RestResult addPhotoTag(Integer photoId, Integer tagId) {
        //查询photo
        Photo photo = photoRepository.findById(photoId).get();
        if (photo.getTagId().isEmpty()) {
            photo.setTagId(String.valueOf(tagId));
        } else {
            //判断是否存在
            List<String> collect = Arrays.stream(photo.getTagId().split(",")).filter(i ->
                    i.equals(String.valueOf(tagId))).collect(Collectors.toList());
            if (collect.size() != 0) {
                return new RestResultBuilder<>().success("标签已经存在");
            }
            String tagIds = photo.getTagId();
            StringBuilder stringBuilder = new StringBuilder(tagIds);
            StringBuilder append = stringBuilder.append("," + tagId);
            photo.setTagId(append.toString());
        }
        photoRepository.save(photo);
        return new RestResultBuilder<>().success("更新成功");
    }

    @Override
    public RestResult deletePhotoTag(Integer photoId, Integer tagId) {
        //查询photo
        Photo photo = photoRepository.findById(photoId).get();
        if (photo.getTagId().isEmpty()) {
            return new RestResultBuilder<>().success("暂无标签");
        } else if (photo.getTagId().contains(",")) {
            //判断是否存在
            List<String> tagIds = new ArrayList<>(Arrays.asList(photo.getTagId().split(",")));
            List<String> collect = tagIds.stream().filter(i ->
                    !i.equals(String.valueOf(tagId))
            ).collect(Collectors.toList());
            photo.setTagId(StringUtil.join(collect, ","));
        } else {
            if (String.valueOf(tagId).equals(photo.getTagId())) {
                photo.setTagId("");
            }
        }
        photoRepository.save(photo);
        return new RestResultBuilder<>().success("删除成功");
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

    @Override
    public RestResult getRecentDelPhoto(int userId) {
        List<Photo> photoList = photoRepository.findRecentDelPhoto(userId);
        Map<String, List<Photo>> resultMap = new HashMap<>();
        for (Photo photo : photoList) {
            resultMap.computeIfAbsent(photo.getUpdateTime().toString().substring(0, 11), k -> new ArrayList<>()).add(photo);
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map.Entry<String, List<Photo>> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            List<Photo> list = entry.getValue();
            List<Photo> list1 = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            for (Photo photo : list) {
                list1.add(Photo.builder().id(photo.getId())
                        .url(photo.getUrl())
                        .isUpload(photo.getIsUpload())
                        .tagId(photo.getTagId())
                        .photoName(photo.getPhotoName())
                        .photoSize(photo.getPhotoSize())
                        .width(photo.getWidth())
                        .height(photo.getHeight())
                        .longitude(photo.getLongitude())
                        .latitude(photo.getLatitude())
                        .userId(photo.getUserId())
                        .createTime(photo.getCreateTime())
                        .updateTime(photo.getUpdateTime()).build());
            }
            map.put("date", key);
            map.put("list", list1);
            resultList.add(map);
        }
        return new RestResultBuilder<>().success(resultList);
    }

    @Override
    public RestResult deleteThorough(List<Integer> ids) {
        List<String> urlList = new ArrayList<>();
        ids.stream().map(i -> {
            Photo photo = photoRepository.findById(i).get();
            urlList.add(photo.getUrl());
            photo.setIsDelete(2);
            photo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            photo.setUrl(null);
            Photo photo1 = photoRepository.saveAndFlush(photo);
            //删除图片索引
            PictureDocument pictureDocument =PictureDocument.builder()
                    .id(i).build();
            pictureRepository.delete(pictureDocument);
          //删除分类
            Identify identify =new Identify();
            identify.setPhotoId(i);
            photo.setIsDelete(2);
            identifyRepository.saveAndFlush(identify);
            return null;
        }).collect(Collectors.toList());
        fileDfsUtil.delete(urlList);
        return new RestResultBuilder<>().success("成功");
    }

    @Override
    public RestResult recoverPhoto(List<Integer> ids) {
        ids.stream().map(i -> {
            Photo photo = photoRepository.findById(i).get();
            photo.setIsDelete(0);
            photo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            Photo photo1 = photoRepository.saveAndFlush(photo);
            return null;
        }).collect(Collectors.toList());
        return new RestResultBuilder<>().success("成功");
    }


    @Override
    public RestResult findPhotoByLocation(int userId, double longitude, double latitude) {
        Location location = LocationUtil.getNearbyLocation(longitude, latitude, 3);

        List<Photo> photoList = photoRepository.findSimilarPhotoByLocation(userId, location);
        return new RestResultBuilder<>().success(photoList);
    }

    @Override
    public RestResult findPhotoToClassification(int userId) {
        List<Photo> photo = photoRepository.findPhoto(userId);
        //如果不存在照片 不分类 直接返回
        if (photo.size() == 0
        ) {
            return new RestResultBuilder<>().success();
        }
        Map<String, List<Photo>> collect = photo.stream().collect(
                Collectors.groupingBy(p ->
                        p.getTime().toString().substring(0, p.getTime().toString().indexOf("-"))
                ));
        List<PhotoClassification> list = new ArrayList<>();
        collect.entrySet().stream().forEach(i -> {
            PhotoClassification photoClassification = new PhotoClassification();
            photoClassification.setPhotoList(i.getValue());
            photoClassification.setYearName(i.getKey());
            list.add(photoClassification);
        });
        return new RestResultBuilder<>().success(list);
    }

    @Override
    public RestResult findPhotoByDay(int userId, String startTime, String endTime) {
        Timestamp startTimestamp = Timestamp.valueOf(startTime.substring(0, 10) + " 00:00:00");
        Timestamp endTimestamp = Timestamp.valueOf(endTime.substring(0, 10) + " 23:59:59");
        List<Photo> photoByDay = photoRepository.findPhotoByDay(userId, startTimestamp, endTimestamp);
        if (photoByDay.size() == 0) {
            return new RestResultBuilder<>().success();
        }
        Map<String, List<Photo>> collect = photoByDay.stream().collect(
                Collectors.groupingBy(p ->
                        p.getTime().toString().substring(0, 10)
                ));
        List<PhotoClassification> list = new ArrayList<>();
        collect.entrySet().stream().forEach(i -> {
            PhotoClassification photoClassification = new PhotoClassification();
            photoClassification.setPhotoList(i.getValue());
            photoClassification.setYearName(i.getKey());
            list.add(photoClassification);
        });
        return new RestResultBuilder<>().success(list);
    }

    @Override
    public RestResult SearchPhoto(int userId, String SearchKey) {
        List<Photo> photoList = photoRepository.SearchPhoto(userId, SearchKey);
        return new RestResultBuilder<>().success(photoList);
    }


}

