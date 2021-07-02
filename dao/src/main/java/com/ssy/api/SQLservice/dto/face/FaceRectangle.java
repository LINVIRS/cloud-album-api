package com.ssy.api.SQLservice.dto.face;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 人脸矩形框对象
 */
public class FaceRectangle {
    // 人脸矩形框左上角 X
    private float upperLeftX;
    // 人脸矩形框左上角 Y
    private float upperLeftY;
    // 人脸矩形框右下角 X
    private float lowerRightX;
    // 人脸矩形框右下角 Y
    private float lowerRightY;
}
