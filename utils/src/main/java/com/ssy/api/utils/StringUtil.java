package com.ssy.api.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenpan on 14/9/16.
 */
public class StringUtil {

    public static String convertRomanNumToCNNum(int num){
        String str = "";

        switch (num){
            case 0:
                str = "零";break;
            case 1:
                str = "一";break;
            case 2:
                str = "二";break;
            case 3:
                str = "三";break;
            case 4:
                str = "四";break;
            case 5:
                str = "五";break;
            case 6:
                str = "六";break;
            case 7:
                str = "七";break;
            case 8:
                str = "八";break;
            case 9:
                str = "九";break;
        }
        return str;
    }

    //判断版本字符串合法性
    public static boolean isCorrectVersion(String str){
        Pattern pattern = Pattern.compile("[0-9]*" + "." + "[0-9]*" + "." + "[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || input.equals("null") || input.equals("\"\""))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }


    public static String trimFirstAndLastChar(String source,char element){
        boolean beginIndexFlag = true;
        boolean endIndexFlag = true;
        do{
            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
            int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();
            source = source.substring(beginIndex, endIndex);
            beginIndexFlag = (source.indexOf(element) == 0);
            endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());
        } while (beginIndexFlag || endIndexFlag);
        return source;
    }


    public static String readFromStream(InputStream stream) throws Exception {

        BufferedReader bf=new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        //最好在将字节流转换为字符流的时候 进行转码
        StringBuffer buffer=new StringBuffer();
        String line="";
        while((line=bf.readLine())!=null){
            buffer.append(line);
        }
        return buffer.toString();

    }

    //判断是否全是数字
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


}
