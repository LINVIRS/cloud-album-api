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

    // 用户

    /**
     * 注册用户
     */
    public static final Integer SMS_CODE_REGISTER_TYPE = 0;
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
    public static final String FastDFSPrefix = "http://36.137.109.33:8888/";

    // 移动云
    public static final String PHOTOAK = "9102f303a29f42d39df26f8c8ecc6c5c";
    public static final String PHOTOSK = "a49bbd02b9d2488fb022606794eaaa3a";
    public static final String FACEAK = "CIDC-AK-ed527649-69c4-4297-93ff-bc8fb34201ee";
    public static final String FACESK = "CIDC-SK-c4b503bb-5cce-45a8-9019-cd3c7694e960";
}
