package com.ssy.api.util;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * RandomString
 *
 * @author wf
 * @date 2021/4/30 15:46
 * @description
 */
public class RandomString {

    public static ArrayList<String> strList = new ArrayList<String>();
    public static Random random = new Random();
    public static final int RANDOM_LENGTH1 = 32;

    static {
        init();
    }

    public static void main(String[] args) {
        String randomStr = generateRandomStr(RANDOM_LENGTH1);
        System.out.println(RANDOM_LENGTH1 + "位随机数:" + randomStr);
        System.out.println(UUID.randomUUID().toString().trim().replace("-","").toUpperCase(Locale.ROOT));
    }

    public static String generateRandomStr(int length) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++) {
            int size = strList.size();
            String randomStr = strList.get(random.nextInt(size));
            sb.append(randomStr);
        }
        return sb.toString();
    }

    public static void init() {
        int begin = 97;
        //生成小写字母,并加入集合
        for(int i = begin; i < begin + 26; i++) {
            strList.add((char)i + "");
        }
        //生成大写字母,并加入集合
        begin = 65;
        for(int i = begin; i < begin + 26; i++) {
            strList.add((char)i + "");
        }
        //将0-9的数字加入集合
        for(int i = 0; i < 10; i++) {
            strList.add(i + "");
        }
    }
}
