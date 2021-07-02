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


    private String pathName = "/Users/yy/Downloads/";
//    private String pathName = "/root/temp/";


    @Override
    public RestResult mergeVideo(MultipartFile[] multipartFiles) {
        List<String> files = new ArrayList<>(10);
        try {
            FileOutputStream fos = null;
            for (MultipartFile multipartFile : multipartFiles) {
                byte[] bytes = multipartFile.getBytes();
                File file = new File(pathName + multipartFile.getOriginalFilename());
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

            // 删除原视频
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
    public RestResult pictureToVideo(MultipartFile[] multipartFiles) {
        List<String> files = new ArrayList<>(10);
        try {
            FileOutputStream fos = null;
            for (MultipartFile multipartFile : multipartFiles) {
                byte[] bytes = multipartFile.getBytes();
                File file = new File(pathName + multipartFile.getOriginalFilename());
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
            String newMergePath = files.get(0).substring(0, index) + "new.mp4";

            fos = new FileOutputStream(txtPath);
            for (String path : files) {
                fos.write(("file '" + path + "'\r\n" + "duration 3" + "\r\n").getBytes());
            }
            fos.write(("file '" + files.get(files.size() - 1)).getBytes());
            fos.close();
            File file = new File(txtPath);

            Result result = VideoUtil.pictureToVideo(file, newMergePath);
            // 上传视频
            Boolean aBoolean = FileUploaderUtil.uploadVideo(newMergePath);

            // 删除原视频
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
    public RestResult convertorWithBgmNoOriginCommon(MultipartFile inputVideo, MultipartFile inputMusic) {
        try {
            FileOutputStream fos = null;
            byte[] bytesV = inputVideo.getBytes();
            byte[] bytesA = inputMusic.getBytes();
            File video = new File(pathName + inputVideo.getOriginalFilename());
            File audio = new File(pathName + inputMusic.getOriginalFilename());
            if (!video.exists() || !audio.exists()) {
                try {
                    video.createNewFile();
                    audio.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fos = new FileOutputStream(video);
            fos.write(bytesV);
            fos = new FileOutputStream(audio);
            fos.write(bytesA);
            String absolutePath = video.getAbsolutePath();
            // 后缀名标识
            int index = absolutePath.lastIndexOf(".");

            String newMergePath = absolutePath.substring(0, index) + "new" + absolutePath.substring(index);


            Result result = VideoUtil.convertorWithBgmNoOriginCommon(absolutePath, newMergePath, audio.getAbsolutePath());
            // 上传视频
            Boolean aBoolean = FileUploaderUtil.uploadVideo(newMergePath);
            // 删除原视频
            if (result.getCode() == 0 && aBoolean) {
                video.delete();
                new File(newMergePath).delete();
                audio.delete();
                // 获取回调
                return new RestResultBuilder<>().success(FileUploaderUtil.getVideo(newMergePath));
            } else {
                log.error(result.getErrMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestResultBuilder<>().success("失败");
    }
}
