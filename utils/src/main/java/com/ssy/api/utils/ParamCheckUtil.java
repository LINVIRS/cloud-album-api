package com.ssy.api.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenpan on 2016/11/11.
 */
public class ParamCheckUtil {

    public static boolean checkRequiredParam(Map params, String... paramNames){

        for (int i = 0; i < paramNames.length; i++){
            String paramName = paramNames[i];
            if (!params.containsKey(paramName)){
                return false;
            }

            Object object = params.get(paramName);

            if (object == null || object.toString().trim().equals("")){
                return false;
            }
        }

        return true;
    }

    public static boolean checkAddress(String address){

        // 根据UnicodeBlock方法判断中文标点符号

        for (int i = 0; i < address.length(); i++){
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(address.charAt(i));
            if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
                return false;
            }
        }

        // 过滤< > &

        Pattern p = Pattern.compile("[<|&|>]");
        Matcher m = p.matcher(address);

        if (m.find()){
            return false;
        }
        return true;
    }

}
