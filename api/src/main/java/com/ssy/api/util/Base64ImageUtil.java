package com.ssy.api.util;

import com.chinamobile.bcop.api.sdk.ai.util.Base64Util;
import com.chinamobile.cmss.sdk.ECloudDefaultClient;
import com.chinamobile.cmss.sdk.ECloudServerException;
import com.chinamobile.cmss.sdk.http.constant.Region;
import com.chinamobile.cmss.sdk.http.signature.Credential;
import com.chinamobile.cmss.sdk.request.EngineImageClassifyDetectPostRequest;
import com.chinamobile.cmss.sdk.response.EngineImageClassifyDetectResponse;
import com.chinamobile.cmss.sdk.response.bean.EngineClassify;
import com.chinamobile.cmss.sdk.util.JacksonUtil;
import com.ssy.api.constant.ParameterConstant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


/**
 * 通用图像识别
 */
public class Base64ImageUtil {
    private static final String accessKey = ParameterConstant.PHOTOAK;
    private static final String secretKey = ParameterConstant.PHOTOSK;

    public static void main(String[] args) {
        File file =new File("C:\\Users\\wanglin\\Desktop\\15.jpg");
        List<EngineClassify> imageClassify = getImageClassify(file);
        System.out.println(imageClassify);
    }
    // 照片分类
    public static List<EngineClassify> getImageClassify(File file) {
        // 初始化
        Credential credential = new Credential(accessKey, secretKey);
        // 初始化 ECloud client,Region 为部署资源池 OP 网关地址枚举类，可自行扩展
        ECloudDefaultClient ecloudClient = new ECloudDefaultClient(credential, Region.POOL_SZ);
        byte[] data = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int len;
            while ((len = fileInputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }

            data = byteArrayOutputStream.toByteArray();

            fileInputStream.close();
            byteArrayOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String base64Content = Base64Util.encode(data);
        // 执行请求，response 类型支持泛型,state != OK 的已经抛出异常
        // 通用物品检测
        List<EngineClassify> body = null;
        try {
            // 通用物品识别
            EngineImageClassifyDetectPostRequest request = new EngineImageClassifyDetectPostRequest();
            request.setImage(base64Content);
            request.setUserId("1");
            EngineImageClassifyDetectResponse response = ecloudClient.call(request);
            if ("OK".equals(response.getState())) {
                // 通用物品检测
                body = response.getBody();
                System.out.println(JacksonUtil.toJson(body));
            }
        } catch (IOException | ECloudServerException e) {
            e.printStackTrace();
        }
        return body;
    }
}
