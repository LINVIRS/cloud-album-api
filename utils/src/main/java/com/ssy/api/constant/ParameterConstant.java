package com.ssy.api.constant;

/**
 * Created by CANONYANG on 2018/6/11.
 */
public class ParameterConstant {

    public final static String AUTHORIZATION = "AUTHORIZATION";
    public static final String JWT_SECRET = "ssyCloudAlbum";
    public static final String WX_TOKEN_PREFIX = "cloud:album:api:";
    public final static String PARAMS = "params";
    public static final String BUCKET_URL_IMG = "";
    public static final String BUCKET_NAME_IMG = "";
    public static final String ACCESS_TOKEN = "access_token";
    // 通用
    public static final String PK_ID = "pkId";
    public static final String ID = "id";
    public static final String LIST = "list";
    public static final String IMG = "img";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String CONTENT = "content";
    public static final String CONTENT_ID = "contentId";
    public static final String PAGENUM = "pageNum";
    public static final String PAGESIZE = "pageSize";
    public static final String TOTAL_NUM = "totalNum";
    public static final String REMARKS = "remarks";
    public static final String SORT = "sort";
    /**
     * 注册用户
     */
    public static final Integer SMS_CODE_REGISTER_TYPE = 0;

    // 用户
    /**
     * 短信登录类型
     */
    public static final Integer SMS_CODE_USER_LOGIN_TYPE = 1;
    public final static String USER_ID = "userId";
    public static final String NAME = "name";
    public static final String NICK_NAME = "nickname";
    public static final String PHONE = "phone";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String SEX = "sex";
    public static final String AVATAR = "avatar";
    // 阿里大于短息平台
    public static final String VERIFICATION_CODE = "verificationCode";
    public static final String SMS_URL = "dysmsapi.aliyuncs.com";
    public static final String APPKEY = "";
    public static final String SECRET = "";
    public static final String SMS_BIND = "SMS_177257277";
    public static final String SMS_MES = "SMS_189762941";
    public static final Integer redisTime = 30;
    // ALIYUN OSS
    public static final String ACCESS_KEY = "";
    public static final String SECRET_KEY = "";
    // FASTDFS
    public static final String FastDFSPrefix = "http://36.137.19.93:7777/";
    public static final String PHOTOAK = "f164d9d562d54d52a23ec8f9a62aa071";
    //message
    public static final String MESSAGE_AK = "LTAI4FpzLFy8uA2PWAXH8cwQ";
    public static final String MESSAGE_SK = "XLTomRADcglUJ5IgRrHxWKJMjPqg8b";
    // 移动云
    public static final String PHOTOSK = "cf148e26f402499b9a9e195271ce6847";

    public static final String FACEAK = "0d56b67d06d84a109f24b7882514cc5d";
    public static final String FACESK = "07574bf1e5ab4597b942f5ad80c2e978";
    //照片信息
    //南北纬
    public static final String GPSLatitudeRef = "GPS Latitude Ref";
    //纬度
    public static final String GPSLatitude = "GPS Latitude";
    //东西经
    public static final String GPSLongitudeRef = "GPS Longitude Ref";
    //经度
    public static final String GPSLongitude = "GPS Longitude";
    //日期
    public static final String PhotoDate = "Date/Time";
    //照片大小
    public static final String FileSize = "File Size";
    //宽度
    public static final String ImageWidth = "Image Width";
    //高度
    public static final String ImageHeight = "Image Height";
    //文件名
    public static final String FileName = "File Name";
    // 视屏剪辑
    public static String pathName = "/Users/yy/Downloads/";
    public static String ffmpegPath = "/Users/yy/Tool/ffmpeg";
}
