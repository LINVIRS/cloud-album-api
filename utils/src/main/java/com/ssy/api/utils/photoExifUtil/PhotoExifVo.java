package com.ssy.api.utils.photoExifUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName: PhotoExifVo
 * @Description: 照片信息Vo
 * @Author: WangLinLIN
 * @Date: 2021/04/21 16:03:58 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoExifVo {
    private String GPSLatitudeRef;
    private String GPSLatitude;
    private String GPSLongitudeRef;
    private String GPSLongitude;
    private String FileName;
    private String ImageHeight;
    private String ImageWidth;
    private String FileSize;
    private String time;
    private String fullPath;


}
