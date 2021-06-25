package com.ssy.api.utils;

/**
 * @ClassName: LocationUtil
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/05/06 21:27:33 
 * @Version: V1.0
 **/
public class LocationUtil {

    /**
     * 根据传入的经纬度和半径范围确定附近的经纬度范围
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param distance  距离 多少千米
     * @return
     */
    public static Location getNearbyLocation(double longitude, double latitude, double distance) {

        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dlng =   Math.abs(2 * Math.asin(Math.sin(distance / (2 * r)) / Math.cos(latitude * Math.PI / 180)));
        dlng = dlng * 180 / Math.PI;//角度转为弧度
        double dlat = distance / r;
        dlat = dlat * 180 / Math.PI;
        double minlat = latitude - dlat;
        double maxlat = latitude + dlat;
        double minlng = longitude - dlng;
        double maxlng = longitude + dlng;
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setMaxLatitude(maxlat);
        location.setMinLatitude(minlat);
        location.setMaxLongitude(maxlng);
        location.setMinLongitude(minlng);
        return location;
    }


    /**
     * 经纬度校验
     * 经度longitude: (?:[0-9]|[1-9][0-9]|1[0-7][0-9]|180)\\.([0-9]{6})
     * 纬度latitude：  (?:[0-9]|[1-8][0-9]|90)\\.([0-9]{6})
     *
     * @return
     */
    public static boolean checkItude(String longitude, String latitude) {
        String reglo = "((?:[0-9]|[1-9][0-9]|1[0-7][0-9])\\.([0-9]{0,6}))|((?:180)\\.([0]{0,6}))";
        String regla = "((?:[0-9]|[1-8][0-9])\\.([0-9]{0,6}))|((?:90)\\.([0]{0,6}))";
        longitude = longitude.trim();
        latitude = latitude.trim();
        return longitude.matches(reglo) == true && latitude.matches(regla);
    }


    /**
     * 求两点之间的距离
     *
     * @param lng1 A点经度
     * @param lat1 A点纬度
     * @param lng2 B点经度
     * @param lat2 B点纬度
     * @return 两点距离
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double EARTH_RADIUS = 6371;
        double radiansAX = Math.toRadians(lng1); // A经弧度
        double radiansAY = Math.toRadians(lat1); // A纬弧度
        double radiansBX = Math.toRadians(lng2); // B经弧度
        double radiansBY = Math.toRadians(lat2); // B纬弧度

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        double acos = Math.acos(cos); // 反余弦值
        return EARTH_RADIUS * acos; // 最终结果
    }

    public static Double tranformPos(String lng) {
        String[] lntArr = lng
                .trim()
                .replace("°", ";")
                .replace("′", ";")
                .replace("'", ";")
                .replace("\"", "")
                .split(";");
        Double result = 0D;
        for (int i = lntArr.length; i > 0; i--) {
            double v = Double.parseDouble(lntArr[i - 1]);
            if (i == 1) {
                result = v + result;
            } else {
                result = (result + v) / 60;
            }
        }
        return result;
    }
}
