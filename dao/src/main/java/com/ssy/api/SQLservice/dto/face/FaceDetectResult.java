package com.ssy.api.SQLservice.dto.face;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 人脸探测结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaceDetectResult {
    private String faceId;
    // 人头像地址
    private String subImage;
    // 识别得分
    private int faceScore;
}
