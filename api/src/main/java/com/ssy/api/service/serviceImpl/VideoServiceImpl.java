package com.ssy.api.service.serviceImpl;

import com.ffmpeg.common.response.Result;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.VideoService;
import com.ssy.api.utils.FileUploaderUtil;
import com.ssy.api.utils.VideoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Override
    public RestResult mergeVideo(MultipartFile[] multipartFiles) {
        List<String> files = new ArrayList<>(10);
        try {
            FileOutputStream fos = null;
            for (MultipartFile multipartFile : multipartFiles) {
                byte[] bytes = multipartFile.getBytes();
                File file = new File("/usr/local/share/cloud-album/" + multipartFile.getOriginalFilename());
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                fos = new FileOutputStream(file);
                fos.write(bytes);
                files.add(file.getAbsolutePath());
            }
            // 后缀名标识
            int index = files.get(0).lastIndexOf(".");
            // 新建合并视频地址文件
            String txtPath = files.get(0).substring(0, index) + ".txt";
            // 合成新的视频的输出目录
            String newMergePath = files.get(0).substring(0, index) + "new" + files.get(0).substring(index);

            fos = new FileOutputStream(txtPath);
            for (String path : files) {
                fos.write(("file '" + path + "'\r\n").getBytes());
            }
            fos.close();
            File file = new File(txtPath);

            Result result = VideoUtil.mergeMultiVideosByFile(file, newMergePath);
            // 上传视频
            Boolean aBoolean = FileUploaderUtil.uploadVideo(newMergePath);

            if (result.getCode() == 0 && aBoolean) {
                file.delete();
                new File(newMergePath).delete();
                for (String s : files) {
                    File f = new File(s);
                    f.delete();
                }
                // 获取回调
                return new RestResultBuilder<>().success(FileUploaderUtil.getVideo(newMergePath));
            } else {
                log.error(result.getErrMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RestResultBuilder<>().success("失败");
    }

    @Override
    public RestResult convertorWithBgmNoOriginCommon(MultipartFile multipartFile) {
        try {
            FileOutputStream fos = null;
            byte[] bytes = multipartFile.getBytes();
            File file = new File("/usr/local/share/cloud-album/" + multipartFile.getOriginalFilename());
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fos = new FileOutputStream(file);
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
