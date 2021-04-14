package com.ssy.api.util.FileUtil.fastdfs;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: sas @Description: TODO @Author: WangLinLIN @Date: 2021/04/10 18:31:22  @Version: V1.0
 */
@Component
@Slf4j
public class FileDfsUtil {

  @Resource private FastFileStorageClient storageClient;

  /** 上传文件 */

  public String upload(MultipartFile multipartFile) {

    String originalFilename =
        multipartFile
            .getOriginalFilename()
            .substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);

    StorePath storePath = null;

    try {
      InputStream inputStream = multipartFile.getInputStream();
      long size = multipartFile.getSize();
      String originalFilename1 = multipartFile.getOriginalFilename();

      storePath =
          storageClient.uploadImageAndCrtThumbImage(
              multipartFile.getInputStream(), multipartFile.getSize(), originalFilename, null);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return storePath.getFullPath();
  }

  /**
   * 只上传文件
   *
   * @return
   * @throws Exception
   */
  public FastImageFile crtFastImageFileOnly(MultipartFile file) throws Exception {
    String fileExtName = FilenameUtils.getExtension(file.getName());
    Set<MetaData> metaDataSet = createMetaData();
    return new FastImageFile.Builder()
        .withFile(file.getInputStream(), file.getSize(), fileExtName)
        .withMetaData(metaDataSet)
        .build();
  }

  /**
   * 上传文件，按默认方式生成缩略图
   *
   * @return
   * @throws Exception
   */
  public FastImageFile crtFastImageAndCrtThumbImageByDefault(MultipartFile file) throws Exception {
    String fileExtName = FilenameUtils.getExtension(file.getName());
    Set<MetaData> metaDataSet = createMetaData();
    return new FastImageFile.Builder()
        .withThumbImage()
        .withFile(file.getInputStream(), file.getSize(), fileExtName)
        .withMetaData(metaDataSet)
        .build();
  }

  /**
   * 上传文件，按设定尺寸方式生成缩略图
   *
   * @return
   * @throws Exception
   */
  public FastImageFile crtFastImageAndCrtThumbImageBySize(MultipartFile file) {
    String fileExtName = FilenameUtils.getExtension(file.getName());
    Set<MetaData> metaDataSet = createMetaData();
    try {
      return new FastImageFile.Builder()
          .withThumbImage(200, 200)
          .withFile(file.getInputStream(), file.getSize(), fileExtName)
          .withMetaData(metaDataSet)
          .build();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 上传文件，按比例生成缩略图
   *
   * @return
   * @throws Exception
   */
  public FastImageFile crtFastImageAndCrtThumbImageByScale(MultipartFile file) throws Exception {
    Set<MetaData> metaDataSet = createMetaData();
    String fileExtName = FilenameUtils.getExtension(file.getName());
    return new FastImageFile.Builder()
        .withThumbImage(0.5) // 50%比例
        .withFile(file.getInputStream(), file.getSize(), fileExtName)
        .withMetaData(metaDataSet)
        .build();
  }
  /**
   * 元数据
   *
   * @return
   */
  private Set<MetaData> createMetaData() {
    Set<MetaData> metaDataSet = new HashSet<MetaData>();
    metaDataSet.add(new MetaData("Author", "tobato"));
    metaDataSet.add(new MetaData("CreateDate", "2016-01-05"));
    return metaDataSet;
  }

