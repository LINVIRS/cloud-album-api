package com.ssy.api.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssy.api.SQLservice.dto.face.*;
import com.ssy.api.SQLservice.entity.Face;
import com.ssy.api.SQLservice.entity.Photo;
import com.ssy.api.SQLservice.repository.FaceRepository;
import com.ssy.api.SQLservice.repository.PhotoRepository;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.service.FaceService;
import com.ssy.api.util.FaceHandlerUtil;
import com.ssy.api.util.FileUtil.fastdfs.FileDfsUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class FaceServiceImpl implements FaceService {
    @Resource
    private FileDfsUtil fileDfsUtil;
    @Resource
    private FaceHandlerUtil faceHandlerUtil;
    @Resource
    private FaceRepository faceRepository;
    @Resource
    private PhotoRepository photoRepository;

    @Override
//    @Async
    public List<FaceDetectResult> faceDetect(String photoUrl) {
        List<FaceDetectResult> faceDetectResults = new ArrayList<>(10);
        // 获取目标图片字节数组
        byte[] imageFromNetByUrl = faceHandlerUtil.getImageFromNetByUrl(photoUrl);
        // 获取探寻照片JSON对象
        JSONObject commonResult = faceHandlerUtil.faceDetect(photoUrl);
        if (commonResult != null) {
            JSONArray faceDetectDetailList = commonResult.getJSONArray("faceDetectDetailList");
            for (Object o : faceDetectDetailList) {
                JSONObject jsonObject = ((JSONObject) o);
                FaceRectangle faceDectectRectangleArea = jsonObject.getObject("faceDectectRectangleArea", FaceRectangle.class);
                String facejosnId = jsonObject.getString("faceId");
                Integer faceScore = jsonObject.getInteger("faceScore");
                // 截取人像
                float upperLeftX = faceDectectRectangleArea.getUpperLeftX();
                float upperLeftY = faceDectectRectangleArea.getUpperLeftY();
                if (upperLeftY < 0) {
                    upperLeftY = 0;
                }
                if (upperLeftX < 0) {
                    upperLeftX = 0;
                }
                byte[] bytes = faceHandlerUtil.subImage(imageFromNetByUrl,
                        upperLeftX,
                        upperLeftY,
                        faceDectectRectangleArea.getLowerRightX() - upperLeftX,
                        faceDectectRectangleArea.getLowerRightY() - faceDectectRectangleArea.getUpperLeftY()
                );
                // 将人像上传fastdfs
                String subImage = fileDfsUtil.upload(bytes);

                // 将未分类的图片添加至人脸大库
                faceAdd(FaceHandlerUtil.FACE_STORE_ALL, photoUrl, "image");
                faceDetectResults.add(FaceDetectResult.builder()
                        .faceId(facejosnId)
                        .faceRectangle(faceDectectRectangleArea)
                        .faceScore(faceScore)
                        .subImage(photoUrl)
                        .build());
            }
            return faceDetectResults;
        }
        return null;
    }

    @Override
    public float faceMatch(String url1, String url2) {
        JSONObject match = faceHandlerUtil.match(url1, url2);
        if (match != null) {
            return match.getFloat("confidence");
        }
        return 0;
    }

    @Override
    public FaceStoreDto createFaceSet(String name, String description) {
        JSONObject faceSet = faceHandlerUtil.createFaceSet(name, description);
        if (faceSet != null) {
            return FaceStoreDto.builder().faceStoreId(faceSet.getInteger("faceStoreId"))
                    .description(description).build();
        }
        return null;
    }

    @Override
    public AddFaceDto faceAdd(Integer faceStoreId, String url, String faceName) {
        JSONObject faceSet = faceHandlerUtil.faceAdd(faceStoreId, url, faceName);
        if (faceSet != null) {
            return AddFaceDto.builder()
                    .faceSetId(faceSet.getInteger("faceSetId"))
                    .faceSetName(faceSet.getString("faceSetName"))
                    .faceId(faceSet.getInteger("faceId"))
                    .faceName(faceSet.getString("faceName")).build();
        }
        return null;
    }

    @Override
    public QueryFaceDto queryFace(Integer faceStoreId, Integer faceId) {
        JSONObject jsonObject = faceHandlerUtil.queryFace(faceStoreId, faceId);
        if (jsonObject != null) {
            QueryFaceDto.builder()
                    .faceName(jsonObject.getString("faceName"))
                    .faceId(jsonObject.getInteger("faceId"))
                    .build();
        }
        return null;
    }

    @Override
    public List<SearchFaceDto> searchFace(String url, Integer faceSetId, Integer faceNumber) {
        List<SearchFaceDto> searchFaceDtos = new ArrayList<>(10);
        JSONObject search = faceHandlerUtil.search(url, faceSetId, faceNumber);
        if (search != null) {
            JSONArray results = search.getJSONArray("results");
            for (Object result : results) {
                JSONObject jsonObject = ((JSONObject) result);
                int id = jsonObject.getInteger("faceId");
                Optional<Face> byId = faceRepository.findById(id);
                if(byId.isPresent()){
                    Face face = byId.get();
                    Photo photo = photoRepository.findDetailById(face.getPhotoId());
                    if (photo != null) {
                        searchFaceDtos.add(
                                SearchFaceDto.builder()
                                        .faceId(jsonObject.getInteger("faceId"))
                                        .url(photo.getUrl())
                                        .faceName(jsonObject.getString("faceName"))
                                        .confidence(jsonObject.getFloat("confidence"))
                                        .photoName(photo.getPhotoName())
                                        .width(photo.getWidth())
                                        .height(photo.getHeight())
                                        .createTime(photo.getCreateTime())
                                        .build()
                        );
                    }
                }

            }
            return searchFaceDtos;
        }
        return null;
    }
}
