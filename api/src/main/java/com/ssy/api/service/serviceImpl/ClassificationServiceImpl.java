package com.ssy.api.service.serviceImpl;

import com.ssy.api.SQLservice.dto.face.*;
import com.ssy.api.SQLservice.entity.Albums;
import com.ssy.api.SQLservice.entity.Face;
import com.ssy.api.SQLservice.entity.FaceStore;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.repository.AlbumRepository;
import com.ssy.api.SQLservice.repository.FaceRepository;
import com.ssy.api.SQLservice.repository.FaceStoreRepository;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.ClassificationService;
import com.ssy.api.service.FaceService;
import com.ssy.api.service.VideoService;
import com.ssy.api.util.FaceHandlerUtil;
import com.ssy.api.util.FileUtil.fastdfs.ThreakPoolFile;
import com.ssy.api.utils.Location;
import com.ssy.api.utils.LocationUtil;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Resource
    private PhotoRepository photoRepository;
    @Resource
    private FaceService faceService;
    @Resource
    private FaceRepository faceRepository;
    @Resource
    private AlbumRepository albumRepository;
    @Resource
    private FaceStoreRepository faceStoreRepository;
    @Resource
    private ThreakPoolFile threakPoolFile;
    @Resource
    private VideoService videoService;

    @Override
    public RestResult faceClassification() {
        List<Photo> photos = photoRepository.findAll();
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
        return null;
    }

    @Override
    public RestResult locationClassification(int userId) {
        List<Photo> photos = photoRepository.findAll();
        ArrayList<List<Photo>> arrayList = new ArrayList<>(10);
        photos.forEach(photo -> {
            Location nearbyLocation = LocationUtil.getNearbyLocation(photo.getLongitude(), photo.getLatitude(), 0.1);
            List<Photo> similarPhotoByLocation = photoRepository.findSimilarPhotoByLocation(userId, nearbyLocation);

            arrayList.add(similarPhotoByLocation);
        });

        // 照片去重复
        ArrayList<List<Photo>> newArr = new ArrayList<>(10);
        for (List<Photo> photoList : arrayList) {
            for (int j = 0; j < photoList.size(); j++) {
                if (!(photoList.containsAll(arrayList.get(j)) && arrayList.get(j).containsAll(photoList))) {
                    newArr.add(photoList);
                }
            }
        }
        ArrayList<String> urls = new ArrayList<>();
        newArr.forEach(list -> {
            List<MultipartFile> files = new ArrayList<>(10);
            // 下载文件
            list.forEach(photo -> {
                String url = photo.getUrl();
                String fullPath = url.substring(url.indexOf("/", 10) + 1);
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                if (threakPoolFile.downloadFiles(System.getProperty("user.dir"), fullPath, fileName)) {
                    String path = System.getProperty("user.dir") + "/" + fileName;
                    File file = new File(path);
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream(file);
                        MultipartFile multipartFile =
                                new MockMultipartFile("copy" + file.getName(), file.getName(),
                                        ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                        files.add(multipartFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            // 生成视频文件
            RestResult restResult = videoService.pictureToVideo(files.toArray(new MultipartFile[0]));
            if (restResult.getCode() == 0) {
                urls.add(restResult.getMsg());
            }
        });

        return new RestResultBuilder<>().success(urls);
    }

    @Override
    public RestResult timeClassification() {
        return null;
    }
}
