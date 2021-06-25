package com.ssy.api.utils;

import io.minio.MinioClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploaderUtil {
    public static Boolean uploadVideo(String path) {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient("http://121.5.235.153:9050", "ssyoss", "ssy123456");
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("ssy-oss");
            if (!isExist) {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("ssy-oss");
            }
            File file = new File(path);
            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject("ssy-oss", file.getName(), path, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getVideo(String path) {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient("http://121.5.235.153:9050", "ssyoss", "ssy123456");
            return minioClient.getObjectUrl("ssy-oss", new File(path).getName());
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
