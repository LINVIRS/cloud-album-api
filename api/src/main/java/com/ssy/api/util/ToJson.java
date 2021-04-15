//package com.ssy.api.util;
//
//
//import net.sf.json.JSONObject;
//
///**

// * @program: client_api
// * @description: string 转成 json串
// * @author: wl
// * @created: 2020/07/09 16:51
// */

//public class ToJson {

//  public static <T> PublicDto getPublicDto(ParamsDto params, Class Tdto) {
//    // json字符串转为JSONObject 对象
//    JSONObject jsonObject = JSONObject.fromObject(params.getParams());
//    JSONObject body1 = jsonObject.getJSONObject("body");
//    // 判断userId是否为空
//    CheckParamsUtil.numIsBlank(jsonObject.getInt("userId"));
//    // jsonObject转成java 类
//    Object o;
//    if (Tdto == null) {
//      o = null;
//    }
//    o = JSONObject.toBean(body1, Tdto);
//    PublicDto publicDto =
//        PublicDto.builder()
//            .userId(jsonObject.getInt("userId"))
//            .body(o)
//            .apiVer(jsonObject.getString("apiVer"))
//            .clientType(jsonObject.getString("clientType"))
//            .clientVer(jsonObject.getString("clientVer"))
//            .deviceToken(jsonObject.getString("deviceToken"))
//            .timestamp(jsonObject.getString("timestamp"))
//            .token(jsonObject.getString("token"))
//            .build();
//
//    return publicDto;
//  }
//}

