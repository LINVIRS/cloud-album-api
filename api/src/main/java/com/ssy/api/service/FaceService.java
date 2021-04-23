package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.face.AddFaceDto;
import com.ssy.api.SQLservice.dto.face.FaceDetectResult;
import com.ssy.api.SQLservice.dto.face.FaceStoreDto;

import java.util.List;
import java.util.concurrent.Future;


public interface FaceService {
    /**
     * 人脸检测
     * 异步检测人脸
     *
     * @param photoUrl
     * @return
     */
    Future<List<FaceDetectResult>> faceDetect(String photoUrl);

    /**
     * 人脸比对
     * 如果两张相同照片则不返回值
     *
     * @param url1
     * @param url2
     * @return
     */
    float faceMatch(String url1, String url2);

    /**
     * 创建人脸库
     *
     * @param name
     * @param description
     * @return
     */
    FaceStoreDto createFaceSet(String name, String description);

    /**
     * 添加人脸到库
     *
     * @param faceStoreId
     * @param url
     * @param faceName
     * @return
     */
    AddFaceDto faceAdd(Integer faceStoreId, String url, String faceName);
}
