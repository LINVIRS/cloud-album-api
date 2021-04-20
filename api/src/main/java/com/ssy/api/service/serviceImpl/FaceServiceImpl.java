package com.ssy.api.service.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssy.api.SQLservice.dto.face.FaceDetectResult;
import com.ssy.api.SQLservice.dto.face.FaceRectangle;
import com.ssy.api.SQLservice.repository.FaceRepository;
import com.ssy.api.service.FaceService;
import com.ssy.api.util.FaceHandlerUtil;
import com.ssy.api.util.FileUtil.fastdfs.FileDfsUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FaceServiceImpl implements FaceService {
    @Resource
    private FileDfsUtil fileDfsUtil;
    @Resource
    private FaceHandlerUtil faceHandlerUtil;


    @Override
    @Async
    public List<FaceDetectResult> faceDetect(String photoUrl) {
        List<FaceDetectResult> faceDetectResults = new ArrayList<>(10);
        // 获取目标图片字节数组
        byte[] imageFromNetByUrl = faceHandlerUtil.getImageFromNetByUrl(photoUrl);
        // 获取探寻照片JSON对象
        JSONObject commonResult = faceHandlerUtil.faceDetect(photoUrl);
        if (commonResult != null) {
            JSONArray faceDetectDetailList = commonResult.getJSONArray("faceDetectDetailList");
            for (Object o : faceDetectDetailList) {
                JSONObject jsonObject = ((JSONObject) o);
                FaceRectangle faceDectectRectangleArea = jsonObject.getObject("faceDectectRectangleArea", FaceRectangle.class);
                String facejosnId = jsonObject.getString("faceId");
                Integer faceScore = jsonObject.getInteger("faceScore");
                // 截取人像
                byte[] bytes = faceHandlerUtil.subImage(imageFromNetByUrl,
                        faceDectectRectangleArea.getUpperLeftX(),
                        faceDectectRectangleArea.getUpperLeftY(),
                        faceDectectRectangleArea.getLowerRightX() - faceDectectRectangleArea.getUpperLeftX(),
                        faceDectectRectangleArea.getLowerRightY() - faceDectectRectangleArea.getUpperLeftY()
                );
                // 将人像上传fastdfs
                String subImage = fileDfsUtil.upload(bytes);
                faceDetectResults.add(FaceDetectResult.builder()
                        .faceId(facejosnId)
                        .faceScore(faceScore)
                        .subImage(subImage)
                        .build());
            }
            return faceDetectResults;
        }
        return null;
    }
}
