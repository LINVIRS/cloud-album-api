package com.ssy.api.service;

import com.ssy.api.SQLservice.dto.face.FaceDetectResult;
import com.ssy.api.result.RestResult;

import java.util.List;

public interface FaceService {
    /**
     * 人脸检测
     *
     * @param photoUrl
     * @return
     */
    List<FaceDetectResult> faceDetect(String photoUrl);
}
