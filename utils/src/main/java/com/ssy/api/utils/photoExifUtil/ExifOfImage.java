package com.ssy.api.utils.photoExifUtil;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.ssy.api.constant.ParameterConstant;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
       InputStream inputStream =new FileInputStream(imagePath);
//        PhotoExifVo metadata = getMetadata();
//        System.out.println(metadata);
    }


    public static PhotoExifVo getMetadata(MultipartFile multipartFile){
        PhotoExifVo photoExifVo =new PhotoExifVo();
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(multipartFile.getInputStream());
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {

                 if (tag.getTagName()== ParameterConstant.GPSLongitudeRef){
                     photoExifVo.setGPSLongitudeRef(tag.getDescription());
                 }
                    if (tag.getTagName()== ParameterConstant.GPSLongitude){
                        photoExifVo.setGPSLongitude(tag.getDescription());
                    }
                    if (tag.getTagName()== ParameterConstant.GPSLatitudeRef){
                        photoExifVo.setGPSLatitudeRef(tag.getDescription());
                    }
                    if (tag.getTagName()== ParameterConstant.GPSLatitude){
                        photoExifVo.setGPSLatitude(tag.getDescription());
                    }
                        //无法获取
//                   double fileSize= Double.parseDouble(tag.getDescription().substring(0, tag.getDescription().indexOf(" ")));
                        DecimalFormat df = new DecimalFormat( "0.00");
                        photoExifVo.setFileSize(df.format(multipartFile.getSize()/8/1024));
                    if (tag.getTagName()== ParameterConstant.PhotoDate){
                        photoExifVo.setTime(  tag.getDescription());
                    }

                        photoExifVo.setFileName(multipartFile.getOriginalFilename());

                    if (tag.getTagName()== ParameterConstant.ImageWidth){

                        photoExifVo.setImageWidth(tag.getDescription().substring(0,tag.getDescription().indexOf(" ")));
                    }
                    if (tag.getTagName()== ParameterConstant.ImageHeight){
                        photoExifVo.setImageHeight(tag.getDescription().substring(0,tag.getDescription().indexOf(" ")));
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
}
