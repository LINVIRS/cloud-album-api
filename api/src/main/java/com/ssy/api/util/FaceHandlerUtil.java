package com.ssy.api.util;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.bcop.api.sdk.ai.core.constant.Region;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
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
    private static final String accessKey = ParameterConstant.FACEAK;
    private static final String secretKey = ParameterConstant.FACESK;
    private static AiFaceBody aiFaceBody = new AiFaceBody(Region.POOL_CS, accessKey, secretKey);

    public static void main(String[] args) {

        byte[] imageFromNetByUrl = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/09/rBEAA2B9I-KAKMbjAAAZVV6nqVM34.jpeg");
        byte[] imageFromNetByUrl1 = new FaceHandlerUtil().getImageFromNetByUrl("http://36.137.109.33:8888/group1/M00/00/09/rBEAA2B9JDmAabs8AAAVgUJknQI85.jpeg");
        //人脸识别与人体识别
//        AiFaceBody aiFaceBody = FaceHandlerUtil.getInstance();
        try {
//            CommonJsonObjectResponse response = aiFaceBody.faceDetect("/Users/yy/Pictures/篮子/篮子9.jpeg", null);
            CommonJsonObjectResponse response = aiFaceBody.faceDetect("/Users/yy/Pictures/7.JPG", null);
//            CommonJsonObjectResponse commonJsonObjectResponse =
//                    aiFaceBody.faceMatch(imageFromNetByUrl, imageFromNetByUrl1, null);
//            CommonJsonObjectResponse faceSet = aiFaceBody.createFaceSet("篮子", "抽象", accessKey, null);
//            System.out.println(commonJsonObjectResponse);
            System.out.println(response);
//            System.out.println(faceSet);
//            MultipartFile multipartFile = subImage(imageFromNetByUrl, (int) 60.18886058032513, (int) 28.9283454567194, (int) 87.51813979446887, (int) 123.607926171273);
//            System.out.println(multipartFile);
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