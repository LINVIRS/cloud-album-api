package com.ssy.api.util.FileUtil.fastdfs;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @ClassName: FileThreadPool @Description: 文件线程池 @Author: WangLinLIN @Date:
 * 2021/04/10 14:46:39  @Version: V1.0
 */
@Component
@Data
public class FileThreadTask implements Callable<String> {

  private MultipartFile multipartFiles;

  @Resource private FileDfsUtil fileDfsUtil;

  @Override
  public String call() throws Exception {
    // 上传图片
    List<String> paths = new ArrayList<>();
    return fileDfsUtil.upload(multipartFiles);
  }

}
