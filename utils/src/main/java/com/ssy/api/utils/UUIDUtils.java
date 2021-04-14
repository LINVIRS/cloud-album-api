package com.ssy.api.utils;

import java.util.UUID;

/**
 * UUID生成工具类
 */
public class UUIDUtils {
    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "").toString();
    }

}
