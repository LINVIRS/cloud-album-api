package com.ssy.api.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinhaijin on 15/6/1.
 */
public interface RedisService {
    void set(String groupKey, String key, Object object, long liveTime);

    Long hIncr(String groupKey, String key);

    void setSorted(String groupKey, String key, double score);

    Long getRank(String groupKey, String key);

    Long getZRank(String groupKey, String key);

    Set getRevRange(String set, long start, long length);

    double getZSetScore(String h, String k);



    void setSet(String set, String key);

    Object get(String groupKey, String key);

    Set getAllKeys(String key);

    void del(String groupKey, String key);

    void setValue(String key, Object object, long liveTime);

    Object getValue(String key);

    void setExpire(String key, long liveTime);

    void setExpire(String key, long liveTime, TimeUnit timeUnit);

    void removeHash(String group, String key);

    void hMutiRemove(String group, List<String> keys);

    void hRemove(String h, Object hk);

    List<Object> hGetAll(String key);

    Set<Object> hGetAllKeys(String key);

    Object hGet(String h, Object o);

    void hSetAll(String key, Map map);

    void hSet(String groupKey, Object hk, Object v);

    void hIntIncre(String groupKey, Object hk, long increment);

    void remove(String groupKey);

    Object vGet(String h);

    List<Object> vMultiGet(List<String> keys);

    void vSet(String h, String v);

    long vIncre(String h, long incre);

    boolean setContain(String key, String value);

    List<Object> getByGroup(String groupKey);

}
