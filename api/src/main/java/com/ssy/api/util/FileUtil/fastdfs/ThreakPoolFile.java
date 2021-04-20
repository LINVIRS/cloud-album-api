package com.ssy.api.util.FileUtil.fastdfs;

import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: ThreakPoolFile @Description: TODO @Author: WangLinLIN @Date: 2021/04/10
 * 22:30:04 @Version: V1.0
 */
@Component
public class ThreakPoolFile {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(ThreakPoolFile.class);

    @Autowired
    protected AppendFileStorageClient storageClient;
    @Resource
    private FileThreadTask fileThreadTask;

    private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            // commons-io
            // IOUtils.copy(inputStream, outputStream);

        }
    }

    /**
     * 创建线程池
     *
     * @return
     */
    public List<String> getResultUpload(MultipartFile[] multipartFiles) {
        int maxPoolSize = Runtime.getRuntime().availableProcessors();
        List<String> returnValue = new ArrayList<>();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(6, maxPoolSize, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (MultipartFile multipartFile : multipartFiles) {
            fileThreadTask.setMultipartFiles(multipartFile);
            Future<String> paths = threadPoolExecutor.submit(fileThreadTask);

            try {
                returnValue.add(paths.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            if (threadPoolExecutor.getTaskCount() == multipartFiles.length) {
                threadPoolExecutor.shutdown();
            }
            break;
        }
        return returnValue;
    }

    public void downloadFiles(String url, String fullPath, String filename) {
        InputStream ins = null;
        DownloadByteArray callback = new DownloadByteArray();
        String group = fullPath.substring(0, fullPath.indexOf("/"));
        String path = fullPath.substring(fullPath.indexOf("/") + 1);
        if (filename.isEmpty()) {
            filename = fullPath.substring(fullPath.lastIndexOf("/") + 1);
        }
        byte[] content = storageClient.downloadFile(group, path, callback);
        ins = new ByteArrayInputStream(content);
        File file = new File(url + "/" + filename);
        try {
            copyInputStreamToFile(ins, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
