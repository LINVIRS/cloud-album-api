package com.ssy.api.utils.photoExifUtil;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.ssy.api.constant.ParameterConstant;
import com.ssy.api.utils.LocationUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: ExifOfImage
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/04/20 10:52:41 
 * @Version: V1.0
 **/
public class ExifOfImage {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath1 = "C:\\Users\\wanglin\\Desktop\\新建文件夹\\刘璇\\beauty1.png";
        String filePath = "C:\\Users\\wanglin\\Desktop\\刘璇\\1.PNG";
        File imagePath = new File(filePath1);
        InputStream inputStream = new FileInputStream(imagePath);

        PhotoExifVo metadata = getMetadataInputStream(inputStream);

    }


    public static PhotoExifVo getMetadata(MultipartFile multipartFile) {
        PhotoExifVo photoExifVo = new PhotoExifVo();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getInputStream());
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {

                    if (tag.getTagName() == ParameterConstant.GPSLongitudeRef) {
                        photoExifVo.setGPSLongitudeRef(tag.getDescription());
                    }
                    if (tag.getTagName() == ParameterConstant.GPSLongitude) {
                        Double longitude = LocationUtil.tranformPos(tag.getDescription());

                        photoExifVo.setGPSLongitude(String.valueOf(longitude));
                    }
                    if (tag.getTagName() == ParameterConstant.GPSLatitudeRef) {
                        photoExifVo.setGPSLatitudeRef(tag.getDescription());
                    }
                    if (tag.getTagName() == ParameterConstant.GPSLatitude) {
                        Double latitude = LocationUtil.tranformPos(tag.getDescription());
                        photoExifVo.setGPSLatitude(String.valueOf(latitude));
                    }
                    //无法获取
//                   double fileSize= Double.parseDouble(tag.getDescription().substring(0, tag.getDescription().indexOf(" ")));
                    DecimalFormat df = new DecimalFormat("0.00");
                    photoExifVo.setFileSize(df.format(multipartFile.getSize() / 8 / 1024));
                    if (tag.getTagName() == ParameterConstant.PhotoDate) {
                        photoExifVo.setTime(tag.getDescription());
                    }

                    photoExifVo.setFileName(multipartFile.getOriginalFilename());

                    if (tag.getTagName() == ParameterConstant.ImageWidth) {
                        photoExifVo.setImageWidth(tag.getDescription().replaceAll("[^0-9]", ""));
                    }
                    if (tag.getTagName() == ParameterConstant.ImageHeight) {
                        photoExifVo.setImageHeight(tag.getDescription().replaceAll("[^0-9]", ""));
                    }
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s", error);
                    }
                }
            }
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photoExifVo;
    }

    public static PhotoExifVo getInputStreamMetadata(InputStream inputStream) {
        PhotoExifVo photoExifVo = new PhotoExifVo();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {

                    if (tag.getTagName() == ParameterConstant.GPSLongitudeRef) {
                        photoExifVo.setGPSLongitudeRef(tag.getDescription());
                    }
                    if (tag.getTagName() == ParameterConstant.GPSLongitude) {
                        Double longitude = LocationUtil.tranformPos(tag.getDescription());

                        photoExifVo.setGPSLongitude(String.valueOf(longitude));
                    }
                    if (tag.getTagName() == ParameterConstant.GPSLatitudeRef) {
                        photoExifVo.setGPSLatitudeRef(tag.getDescription());
                    }
                    if (tag.getTagName() == ParameterConstant.GPSLatitude) {
                        Double latitude = LocationUtil.tranformPos(tag.getDescription());
                        photoExifVo.setGPSLatitude(String.valueOf(latitude));
                    }
                    //无法获取
//                   double fileSize= Double.parseDouble(tag.getDescription().substring(0, tag.getDescription().indexOf(" ")));
                    DecimalFormat df = new DecimalFormat("0.00");
                    photoExifVo.setFileSize(df.format(inputStream.available() / 8 / 1024));
                    if (tag.getTagName() == ParameterConstant.PhotoDate) {
                        photoExifVo.setTime(tag.getDescription());
                    }

                    photoExifVo.setFileName(null);

                    if (tag.getTagName() == ParameterConstant.ImageWidth) {
                        photoExifVo.setImageWidth(tag.getDescription().replaceAll("[^0-9]", ""));
                    }
                    if (tag.getTagName() == ParameterConstant.ImageHeight) {
                        photoExifVo.setImageHeight(tag.getDescription().replaceAll("[^0-9]", ""));
                    }
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s", error);
                    }
                }
            }
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photoExifVo;
    }
    public static PhotoExifVo getMetadataInputStream(InputStream multipartFile) {
        PhotoExifVo photoExifVo = new PhotoExifVo();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(multipartFile);
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {

                    System.out.println(tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s", error);
                    }
                }
            }
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * url转变为 MultipartFile对象
     * @param url
     * @param fileName
     * @return
     * @throws Exception
     */
    /**
     * 根据url 拉取文件
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static File getFile(String url) throws Exception {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("."), url.length());
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("fwj_url", fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     * 根据url获取图片并转换为multipartFile类型
     *
     * @param url
     * @return
     */
    public static InputStream getInputStream(String url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setReadTimeout(30000);
        conn.setConnectTimeout(30000);
        //设置应用程序要从网络连接读取数据
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return conn.getInputStream();
        }
        return null;
    }
}
