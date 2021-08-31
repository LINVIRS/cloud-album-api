package com.ssy.api.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.awt.*;
import java.io.ByteArrayOutputStream;

/**
 * @ClassName: QrCodeUtil @Description: 二维码生成类 @Author: WangLinLIN @Date:
 * 2021/04/12 10:46:48  @Version: V1.0
 */
public class MyQrCodeUtil {
  /**
   * 根据宽高生成二维码
   *
   * @param text
   * @param width
   * @param height
   * @return
   */
  public static String generateQrCode(String text, int width, int height) {
    QrConfig config = new QrConfig(width, height);
    // 设置边距，既二维码和背景之间的边距
    config.setMargin(1);
    // 设置前景色，既二维码颜色（青色）
    config.setForeColor(Color.BLACK.getRGB());
    // 设置背景色（灰色）
    config.setBackColor(Color.WHITE.getRGB());
    // 生成二维码到文件，也可以到流
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    QrCodeUtil.generate(text, config, ImgUtil.IMAGE_TYPE_PNG, outputStream);
    byte[] pngData = outputStream.toByteArray();
    // 这个前缀，可前端加，可后端加，不加的话，不能识别为图片
    return "data:image/png;base64," + Base64.encode(pngData);
  }
}
