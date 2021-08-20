package com.ssy.api.util;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.bcop.api.sdk.ai.core.constant.Region;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.ssy.api.SQLservice.dto.face.MyAiFaceBody;
import com.ssy.api.constant.ParameterConstant;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class FaceHandlerUtil {
    public static final Integer FACE_STORE_ALL = 119200;

    private static final String accessKey = ParameterConstant.FACEAK;
    private static final String secretKey = ParameterConstant.FACESK;
    private static final MyAiFaceBody aiFaceBody = new MyAiFaceBody(Region.POOL_CS, accessKey, secretKey);

    public static void main(String[] args) {
        FaceHandlerUtil faceHandlerUtil = new FaceHandlerUtil();
//        byte[] imageFromNetByUrl = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/09/rBEAA2B9I-KAKMbjAAAZVV6nqVM34.jpeg");
//        byte[] imageFromNetByUrl1 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/09/rBEAA2B9JDmAabs8AAAVgUJknQI85.jpeg");
//        byte[] imageFromNetByUrl4 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/0C/rBEAA2CA6juAV3WTAAAhoM3aP7499.jpeg");
//        byte[] imageFromNetByUrl5 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/0C/rBEAA2CA6liAC31_AAAfOkec7vI84.jpeg");
//        byte[] imageFromNetByUrl2 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/0A/rBEAA2B_wpGAd5-MAAAbaEHTwU461.jpeg");
//        byte[] imageFromNetByUrl3 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/0C/rBEAA2B_7peARXwEAAAWhMiH-e096.jpeg");
//        byte[] imageFromNetByUrl10 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/0F/rBEAA2CGfRyAU71rAABpAUdghn8075.jpg");
//        byte[] imageFromNetByUrl6 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/0D/rBEAA2CD03OAXUu9AADi6rB8uto752.jpg");
//        byte[] imageFromNetByUrl6 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.19.93:7777/group1/M00/00/00/rBEAA2Ed0heAWlaXAAEXBortGgM211.PNG");

        try {
//            String url = "https://image.ijq.tv/201911/28/11-35-47-21-46.jpg";
//            System.out.println(new FaceHandlerUtil().faceAdd(116191, url, "测试"));
//            System.out.println(aiFaceBody.createFaceSet("人脸大库", "自定义", accessKey, null).getCommonResult());
//            System.out.println(aiFaceBody.queryFace(116171, 62251, accessKey, null));
//            System.out.println(aiFaceBody.createFaceToFile(FACE_STORE_ALL, imageFromNetByUrl5, "篮子哥2", null, accessKey, null));
//            System.out.println(aiFaceBody.queryFace(FACE_STORE_ALL, 62257, accessKey, null).getCommonResult());
//            System.out.println(aiFaceBody.queryFaceSet("人脸大库", 0, 100, accessKey, null));
//            System.out.println(aiFaceBody.faceSearch(imageFromNetByUrl, FACE_STORE_ALL.toString(), 10, accessKey, null).getCommonResult());
//            System.out.println(faceSet);
//            System.out.println(aiFaceBody.faceDetect(imageFromNetByUrl6, null));
            System.out.println(faceHandlerUtil.faceDetect("http://36.137.19.93:7777/group1/M00/00/00/rBEAA2Ed0heAWlaXAAEXBortGgM211.PNG"));
//            System.out.println(faceHandlerUtil.faceAdd(FACE_STORE_ALL,"http://121.5.235.153:7777/group1/M00/00/06/rBEABGDipY-AZdFCAAAYxWS4Mqo829.jpg","image"));
//            System.out.println(faceHandlerUtil.search("http://121.5.235.153:7777/group1/M00/00/07/rBEABGDir0eAULJtAAsX_FN18MA205.JPG",
//                    119200,10 ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人脸探寻
     *
     * @param url 图片地址
     * @return
     */
    public JSONObject faceDetect(String url) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse = aiFaceBody.faceDetect(getImageFromNetByUrl(url), null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 人脸匹配
     *
     * @param url1
     * @param url2
     * @return
     */
    public JSONObject match(String url1, String url2) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse = aiFaceBody.faceMatch(getImageFromNetByUrl(url1), getImageFromNetByUrl(url2), null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 添加人脸
     *
     * @param faceStoreId 人脸库id
     * @param url         人脸照片地址
     * @param faceName    照片名称
     * @return
     */
    public JSONObject faceAdd(Integer faceStoreId, String url, String faceName) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse =
                    aiFaceBody.createFaceToFile(faceStoreId, getImageFromNetByUrl(url), faceName, null, accessKey, null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 查询人脸
     *
     * @param faceStoreId
     * @param faceId
     * @return
     */
    public JSONObject queryFace(Integer faceStoreId, Integer faceId) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse =
                    aiFaceBody.queryFace(faceStoreId, faceId, accessKey, null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 删除人脸
     *
     * @param faceStoreId
     * @param faceId
     * @return
     */
    public JSONObject deleteFace(Integer faceStoreId, Integer faceId) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse =
                    aiFaceBody.deleteFace(faceStoreId, faceId, accessKey, null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }


    /**
     * 创建人脸库
     *
     * @param name
     * @param description
     * @return
     */
    public JSONObject createFaceSet(String name, String description) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse = aiFaceBody.createFaceSet(name, description, accessKey, null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 删除人脸库
     *
     * @param faceSetId
     * @return
     */
    public JSONObject deleteFaceSet(String faceSetId) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse = aiFaceBody.deleteFaceSet(faceSetId, accessKey, null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 人脸库查询
     *
     * @param name
     * @param page
     * @param size
     * @return
     */
    public JSONObject queryFaceSet(String name, Integer page, Integer size) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse = aiFaceBody.queryFaceSet(name, page, size, accessKey, null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 人脸搜索
     * @param url
     * @param faceSetId
     * @param faceNumber
     * @return
     */
    public JSONObject search(String url, Integer faceSetId, Integer faceNumber) {
        JSONObject commonResult = null;
        try {
            CommonJsonObjectResponse commonJsonObjectResponse = aiFaceBody.faceSearch(getImageFromNetByUrl(url), faceSetId.toString(), faceNumber, accessKey, null);
            if (commonJsonObjectResponse.getHttpCode() == 200) {
                commonResult = commonJsonObjectResponse.getCommonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResult;
    }

    /**
     * 剪裁图片
     *
     * @param bytes  图片
     * @param x      横向起始位置
     * @param y      纵向起始位置
     * @param width  宽度
     * @param height 宽度
     * @return 处理后的图片数组
     * @throws Exception
     */
    public byte[] subImage(byte[] bytes, float x, float y, float width, float height) {
        ByteArrayOutputStream outStream = null;
        try {
            // 读取字节数组
            InputStream is = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(is);
            BufferedImage subImage = image.getSubimage((int) x, (int) y, (int) width, (int) height);
            // 图片主转为字节数组
            outStream = new ByteArrayOutputStream();
            ImageIO.write(subImage, "jpg", outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outStream.toByteArray();
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            // 通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            // 得到图片的二进制数据
            return readInputStream(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    private byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}