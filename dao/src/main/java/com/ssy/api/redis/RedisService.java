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

    public void setValue(String key, Object object, long liveTime);

    public Object getValue(String key);

    public void setExpire(String key, long liveTime);

    public void setExpire(String key, long liveTime, TimeUnit timeUnit);

    public void removeHash(String group, String key);

    public void hMutiRemove(String group, List<String> keys);

    public void hRemove(String h, Object hk);

    public List<Object> hGetAll(String key);

    public Set<Object> hGetAllKeys(String key);

    public Object hGet(String h, Object o);

    public void hSetAll(String key, Map map);

    public void hSet(String groupKey, Object hk, Object v);

    public void hIntIncre(String groupKey, Object hk, long increment);

    public void remove(String groupKey);

    public Object vGet(String h);

    public List<Object> vMultiGet(List<String> keys);

    public void vSet(String h, String v);

    public long vIncre(String h, long incre);

    public boolean setContain(String key, String value);


}
