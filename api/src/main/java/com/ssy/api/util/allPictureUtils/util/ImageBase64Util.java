package com.ssy.api.util.allPictureUtils.util;

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @ClassName: asda
 * @Description: TODO
 * @Author: WangLinLIN
 * @Date: 2021/07/01 22:15:19 
 * @Version: V1.0
 **/
public class ImageBase64Util {

    private static String ImageFormat = "jpg";


    /**
     * 按照比例和规格压缩图片得到base64图片字符串
     *
     * @return
     */
    public static String resizeImage(String filePath) {
        try {
            int maxSize = 2900; // kb
            BufferedImage src = fileToBufferedImage(filePath);
            int srcWdith = src.getWidth();
            int srcHeigth = src.getHeight();
            BufferedImage output = Thumbnails.of(src).size(srcWdith, srcHeigth).imageType(BufferedImage.TYPE_INT_RGB).asBufferedImage();

            //判断图片格式
            if (filePath!=null && filePath.length()>3) {
                String substring = filePath.substring(filePath.length() - 3);
                ImageFormat = substring;
            }
            String base64 = imageToBase64(output);
            while (base64.length() - base64.length() / 8 * 2 > maxSize * 1000) {
                output = Thumbnails.of(output).scale(0.9f).asBufferedImage();
                base64 = imageToBase64(output);
            }
            return base64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片文件转BufferedImage
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static BufferedImage fileToBufferedImage(String filePath) throws Exception {
        String path = filePath.substring(filePath.lastIndexOf("/") + 1,filePath.length());
        String encode = null;
        try {
            encode = URLEncoder.encode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        filePath = filePath.substring(0,filePath.lastIndexOf("/") +1) + encode;

        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;
        BufferedImage img = null;
        try {
            //判断网络链接图片文件/本地目录图片文件

                // 创建URL
                URL url = new URL(filePath);
                // 创建链接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;//连接失败/链接失效/图片不存在
            }
            int contentLength = conn.getContentLength();

            inputStream = conn.getInputStream();
                outputStream = new ByteArrayOutputStream();
                // 将内容读取内存中
                buffer = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                buffer = outputStream.toByteArray();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
            img = ImageIO.read(byteArrayInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return img;
    }

    /**
     * 将base64字符转换为输入流
     *
     * @param base64string
     * @return
     */
    private static InputStream base64StringToInputStream(String base64string) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64string.getBytes());
        InputStream inputStream = byteArrayInputStream;
        return inputStream;
    }

    /**
     * 将BufferedImage转换为base64字符串
     *
     * @param bufferedImage
     * @return
     */
    public static String imageToBase64(BufferedImage bufferedImage) {
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, ImageFormat, baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoder.encode((baos.toByteArray())));
    }

}

