package com.ssy.api.util;

import com.chinamobile.bcop.api.sdk.ai.core.constant.Region;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;

public class FaceHandlerUtil {
    private static AiFaceBody aiFaceBody;
    private static String accessKey = "CIDC-AK-2e69a452-02a8-4285-b6b3-3ff661c3d784";
    private static String secretKey = "CIDC-SK-c48411b7-c75d-41de-b34e-b053c53af030";

    public static AiFaceBody getInstance() {
        if (aiFaceBody == null) {
            aiFaceBody = new AiFaceBody(Region.POOL_CS, accessKey, secretKey);
        }
        return aiFaceBody;
    }


    public static void main(String[] args) {

        //人脸识别与人体识别
        AiFaceBody aiFaceBody = FaceHandlerUtil.getInstance();
        try {
            CommonJsonObjectResponse response = aiFaceBody.faceDetect("/Users/yy/Pictures/篮子/篮子9.jpeg", null);
            CommonJsonObjectResponse commonJsonObjectResponse =
                    aiFaceBody.faceMatch("/Users/yy/Pictures/篮子/篮子2.jpeg", "/Users/yy/Pictures/篮子/篮子9.jpeg", null);

            System.out.println(commonJsonObjectResponse);
            System.out.println(response.getCommonResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}