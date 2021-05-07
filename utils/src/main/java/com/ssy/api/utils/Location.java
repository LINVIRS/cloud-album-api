package com.ssy.api.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Location
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/05/06 21:28:22 
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private double latitude;
    private double longitude;
    private double maxLatitude;
    private double maxLongitude;
    private double minLatitude;
    private double minLongitude;

}
