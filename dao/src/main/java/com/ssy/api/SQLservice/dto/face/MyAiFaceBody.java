package com.ssy.api.SQLservice.dto.face;

import com.chinamobile.bcop.api.sdk.ai.core.constant.Region;
import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.core.signature.Credential;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.chinamobile.bcop.api.sdk.ai.facebody.DeleteFaceRequest;
import com.chinamobile.bcop.api.sdk.ai.facebody.FaceSearchRequest;
import com.chinamobile.bcop.api.sdk.ai.facebody.QueryFaceRequest;
import com.chinamobile.bcop.api.sdk.ai.util.Base64Util;

import java.util.HashMap;
import java.util.Iterator;

public class MyAiFaceBody extends AiFaceBody {
    public MyAiFaceBody(Region region, String accessKey, String secretKey) {
        super(region, accessKey, secretKey);
    }

    public MyAiFaceBody(Region region, Credential credential) {
        super(region, credential);
    }

    @Override
    public CommonJsonObjectResponse faceDetect(byte[] imageFile, HashMap<String, String> options) throws ClientException {
        return super.faceDetect(imageFile, options);
    }

    @Override
    public CommonJsonObjectResponse faceDetect(String imageFile, HashMap<String, String> options) throws ClientException {
        return super.faceDetect(imageFile, options);
    }

    @Override
    public CommonJsonObjectResponse faceMatch(byte[] imageFile1, byte[] imageFile2, HashMap<String, String> options) throws ClientException {
        return super.faceMatch(imageFile1, imageFile2, options);
    }

    @Override
    public CommonJsonObjectResponse faceMatch(String imageFile1, String imageFile2, HashMap<String, String> options) throws ClientException {
        return super.faceMatch(imageFile1, imageFile2, options);
    }

    @Override
    public CommonJsonObjectResponse faceSearch(String imageFile, String faceSetId, Integer faceNumber, String appkey, HashMap<String, String> options) throws ClientException {
        return super.faceSearch(imageFile, faceSetId, faceNumber, appkey, options);
    }

    @Override
    public CommonJsonObjectResponse faceSearch(byte[] imageFile, String faceSetId, Integer faceNumber, String appkey, HashMap<String, String> options) throws ClientException {
        FaceSearchRequest request = new FaceSearchRequest(faceSetId, faceNumber, appkey);
        String base64Content = Base64Util.encode(imageFile);
        request.setImageFile(base64Content);
        if (options != null) {
            for (String key : options.keySet()) {
                request.putBody(key, options.get(key));
            }
        }

        return super.execute(request);
    }

    @Override
    public CommonJsonObjectResponse createFaceSet(String faceSetName, String faceSetDescription, String appkey, HashMap<String, String> options) throws ClientException {
        return super.createFaceSet(faceSetName, faceSetDescription, appkey, options);
    }

    @Override
    public CommonJsonObjectResponse deleteFaceSet(String faceSetIds, String appkey, HashMap<String, String> options) throws ClientException {
        return super.deleteFaceSet(faceSetIds, appkey, options);
    }

    @Override
    public CommonJsonObjectResponse queryFaceSet(String name, Integer page, Integer rows, String appKey, HashMap<String, String> options) throws ClientException {
        return super.queryFaceSet(name, page, rows, appKey, options);
    }

    @Override
    public CommonJsonObjectResponse createFaceToFile(Integer faceSetId, byte[] imageFile, String faceName, String faceExtraInfo, String appKey, HashMap<String, String> options) throws ClientException {
        return super.createFaceToFile(faceSetId, imageFile, faceName, faceExtraInfo, appKey, options);
    }

    @Override
    public CommonJsonObjectResponse createFaceToFile(Integer faceSetId, String imageFile, String faceName, String faceExtraInfo, String appKey, HashMap<String, String> options) throws ClientException {
        return super.createFaceToFile(faceSetId, imageFile, faceName, faceExtraInfo, appKey, options);
    }

    @Override
    public CommonJsonObjectResponse createFaceToUrl(Integer faceSetId, String imageUrl, String faceName, String faceExtraInfo, String appKey, HashMap<String, String> options) throws ClientException {
        return super.createFaceToUrl(faceSetId, imageUrl, faceName, faceExtraInfo, appKey, options);
    }

    @Override
    public CommonJsonObjectResponse deleteFace(Integer faceSetId, Integer faceId, String appKey, HashMap<String, String> options) throws ClientException {
        DeleteFaceRequest request = new DeleteFaceRequest(faceId, appKey);
        request.setFaceSetId(faceSetId);
        if (options != null) {
            Iterator var6 = options.keySet().iterator();

            while (var6.hasNext()) {
                String key = (String) var6.next();
                request.putBody(key, options.get(key));
            }
        }

        return super.execute(request);
    }

    @Override
    public CommonJsonObjectResponse queryFace(Integer faceSetId, Integer faceId, String appKey, HashMap<String, String> options) throws ClientException {
        QueryFaceRequest request = new QueryFaceRequest(faceId, appKey);
        request.setFaceSetId(faceSetId);
        if (options != null) {
            for (String key : options.keySet()) {
                request.putBody(key, options.get(key));
            }
        }
        return super.execute(request);
    }

    @Override
    public CommonJsonObjectResponse densityDetect(byte[] imageFile, HashMap<String, String> options) throws ClientException {
        return super.densityDetect(imageFile, options);
    }

    @Override
    public CommonJsonObjectResponse densityDetect(String imageFile, HashMap<String, String> options) throws ClientException {
        return super.densityDetect(imageFile, options);
    }

    @Override
    public boolean isEmpty(String value) {
        return super.isEmpty(value);
    }
}
