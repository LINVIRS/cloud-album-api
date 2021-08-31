package com.ssy.api.service;

import com.ssy.api.result.RestResult;

public interface ClassificationService {
    /**
     * 人脸检测分类
     *
     * @return
     */
    RestResult faceClassification();

    /**
     * 地点分类
     *
     * @return
     */
    RestResult locationClassification(int userId);


    /**
     * 时间分类
     *
     * @return
     */
    RestResult timeClassification();
}
