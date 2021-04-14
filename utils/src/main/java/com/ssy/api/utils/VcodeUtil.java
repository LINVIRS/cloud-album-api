package com.ssy.api.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机码生成类
 * Created by yinhaijin on 15/4/30.
 */
public class VcodeUtil {
    /**
     * 成随即码
     *
     * @return
     */
    public static String createCode() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += (int) (Math.random() * 10);
        }
        return result;
    }

    /**
     * 成订单号
     *
     * @return
     */
    public static String createOrderNum(Integer type, Integer goodsType, Integer id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String orderNum = String.valueOf(type) + String.valueOf(goodsType) + dateFormat.format(new Date()) + String.valueOf(id);
        return orderNum;
    }

    public static String creatOrderNo(Integer userId, Long sno){
        Date date = new Date();
        String dateStr = new SimpleDateFormat("DMMdd").format(date);
        String seconds = new SimpleDateFormat("HHmmss").format(date);

        String snoStr = new DecimalFormat("000000").format(sno);

        String userStr = new DecimalFormat("00").format(userId%100);

        String orderNum = dateStr +  userStr + seconds + snoStr;
        return orderNum;
    }


    /**
     * 生成演出编号
     */
    public static String showNo() {
        Date date = new Date();
        String result = "";
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
        String seconds = new SimpleDateFormat("HHmmss").format(date);
        result = dateStr + getTwo() + seconds;
        return result;
    }

    /**
     * -      * 产生随机的2位数
     * -      * @return
     * -
     */
    public static String getTwo() {
        Random rad = new Random();

        String result = rad.nextInt(100) + "";

        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

    public static String getRandomNickname(String prefix, int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOKPRSTUVWSYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer(prefix);
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}
