package com.ssy.api.service.serviceImpl;

import com.ffmpeg.common.response.Result;
import com.ssy.api.SQLservice.dto.PageDto;
import com.ssy.api.SQLservice.entity.Video;
import com.ssy.api.SQLservice.repository.VideoRepository;
import com.ssy.api.SQLservice.vo.VideoVO;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.result.RestResult;
import com.ssy.api.result.RestResultBuilder;
import com.ssy.api.service.VideoService;
import com.ssy.api.utils.FileUploaderUtil;
import com.ssy.api.utils.VideoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoRepository videoRepository;


    @Override
    public RestResult findAll(PageDto pageDto) {
        List<Video> allVideo = videoRepository.findAllVideo(pageDto);
        return new RestResultBuilder<>().success(allVideo);
    }

    @Override
    public RestResult mergeVideo(MultipartFile[] multipartFiles) {
        List<String> files = new ArrayList<>(10);
        try {
            FileOutputStream fos = null;
            for (MultipartFile multipartFile : multipartFiles) {
                byte[] bytes = multipartFile.getBytes();
                File file = new File(ParameterConstant.pathName + multipartFile.getOriginalFilename());
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
                File file = new File(ParameterConstant.pathName + multipartFile.getOriginalFilename());
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
//                file.delete();
//                new File(newMergePath).delete();
//                for (String s : files) {
//                    File f = new File(s);
//                    f.delete();
//                }
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
    public RestResult pictureToVideo(VideoVO videoVO) {
        // 可能会报 像素不能被2整除的错误
        URL profile;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        List<String> urls = new ArrayList<>(10);
        List<String> files = new ArrayList<>(10);
        urls = videoVO.getList();
        try {
            for (String url : urls) {
                profile = new URL(url);
                inputStream = profile.openStream();

                String name = url.substring(url.lastIndexOf("/") + 1);
                File file = new File(ParameterConstant.pathName + name);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                outputStream = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                files.add(file.getAbsolutePath());
            }

            // 后缀名标识
            int index = files.get(0).lastIndexOf(".");
            // 新建合并视频地址文件
            String txtPath = files.get(0).substring(0, index) + ".txt";
            // 合成新的视频的输出目录
            String newMergePath = files.get(0).substring(0, index) + "new.mp4";

            outputStream = new FileOutputStream(txtPath);
            for (String path : files) {
                outputStream.write(("file '" + path + "'\r\n" + "duration 3" + "\r\n").getBytes());
            }
            outputStream.write(("file '" + files.get(files.size() - 1)).getBytes());
            outputStream.close();
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
            File video = new File(ParameterConstant.pathName + inputVideo.getOriginalFilename());
            File audio = new File(ParameterConstant.pathName + inputMusic.getOriginalFilename());
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

    @Override
    public RestResult convertorWithBgmNoOriginCommon(String inputVideo, MultipartFile inputMusic) {
        URL profile;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            byte[] bytesA = inputMusic.getBytes();

            profile = new URL(inputVideo);
            inputStream = profile.openStream();

            String name = inputVideo.substring(inputVideo.lastIndexOf("/") + 1);
            File video = new File(ParameterConstant.pathName + name);

            File audio = new File(ParameterConstant.pathName + inputMusic.getOriginalFilename());
            if (!video.exists() || !audio.exists()) {
                try {
                    video.createNewFile();
                    audio.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputStream = new FileOutputStream(video);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream = new FileOutputStream(audio);
            outputStream.write(bytesA);

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
        return null;
    }
}
