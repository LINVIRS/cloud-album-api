package com.ssy.api.utils;

import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileUploaderUtil {
    @SneakyThrows
    public static Boolean uploadVideo(String path) {
        MinioClient minioClient = null;
        try {

                minioClient = new MinioClient("http://36.137.19.93:9050", "ssy", "ssy123456");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("cloud-album");
            if (!isExist) {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("cloud-album");
            }
            File file = new File(path);
            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject("cloud-album", file.getName(), path, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @SneakyThrows
    public static String getVideo(String path) {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient("http://36.137.19.93:9050", "ssy", "ssy123456");
            return minioClient.getObjectUrl("cloud-album", new File(path).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void multipartFileToFile(MultipartFile multipart, Path dir
    ) throws IOException {
        Path filepath = Paths.get(dir.toString(), multipart.getOriginalFilename());
        multipart.transferTo(filepath);
    }

}
