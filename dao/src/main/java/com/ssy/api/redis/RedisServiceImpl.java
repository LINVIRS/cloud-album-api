package com.ssy.api.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinhaijin on 15/6/1.
 */
@Component(value = "redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key
     * @param object
     * @param liveTime
     */
    @Override
    public void set(String groupKey, String key,  Object object, long liveTime) {
        redisTemplate.opsForHash().put(groupKey,key,object);
//        redisTemplate.expire(key, liveTime, TimeUnit.SECONDS);
    }

    @Override
    public Long hIncr(String groupKey, String key) {
        return redisTemplate.opsForHash().increment(groupKey, key, 1l);
    }

    @Override
    public Set getAllKeys(String key) {
        Set set = redisTemplate.opsForHash().keys(key);
        return set;
    }

    @Override
    public void del(String groupKey, String key) {
        redisTemplate.opsForHash().delete(groupKey,key);
    }

    @Override
    public void removeHash(String group, String key) {

        redisTemplate.opsForHash().delete(group, key);
    }

    @Override
    public void hMutiRemove(String group, List<String> keys) {
        redisTemplate.opsForHash().delete(group, keys.toArray(new String[0]));
    }

    @Override
    public void hRemove(String h, Object hk) {
        redisTemplate.opsForHash().delete(h, hk);
    }


    @Override
    public Object get(String groupKey, String key) {
        Object object = redisTemplate.opsForHash().get(groupKey, key);
        return object;
    }

    @Override
    public List<Object> hGetAll(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    @Override
    public Set<Object> hGetAllKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Object hGet(String h, Object o) {
        return redisTemplate.opsForHash().get(h, o);
    }

    @Override
    public void hSetAll(String key, Map map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public void hSet(String groupKey, Object hk, Object v) {
        redisTemplate.opsForHash().put(groupKey, hk, v);
    }

    @Override
    public void hIntIncre(String groupKey, Object hk, long increment) {
        redisTemplate.opsForHash().increment(groupKey, hk, increment);
    }


    @Override
    public void setSorted(String groupKey, String key, double score) {
        redisTemplate.boundZSetOps(groupKey).add(key,score);
    }

    @Override
    public Long getRank(String groupKey, String key) {

        Long rank = redisTemplate.boundZSetOps(groupKey).reverseRank(key);

        if (rank != null){
            return rank + 1;
        }else{
            return null;
        }
    }

    @Override
    public Long getZRank(String groupKey, String key) {

        Long rank = redisTemplate.boundZSetOps(groupKey).rank(key);

        if (rank != null){
            return rank + 1;
        }else{
            return null;
        }
    }

    @Override
    public Set getRevRange(String set, long start, long length) {
        return redisTemplate.boundZSetOps(set).reverseRange(start, length);
    }

    @Override
    public double getZSetScore(String h, String k) {



        return redisTemplate.opsForZSet().score(h, k);
    }

    @Override
    public void setSet(String set, String key) {
        redisTemplate.opsForSet().add(set, key);
    }

    @Override
    public void setValue(String key,  Object object, long liveTime){
        redisTemplate.opsForValue().set(key, object, liveTime, TimeUnit.SECONDS);
    }

    @Override
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setExpire(String key, long liveTime) {
        redisTemplate.expire(key, liveTime, TimeUnit.MINUTES);
    }

    @Override
    public void setExpire(String key, long liveTime, TimeUnit timeUnit) {
        redisTemplate.expire(key, liveTime, TimeUnit.MINUTES);
    }

    @Override
    public void remove(String groupKey) {
        redisTemplate.delete(groupKey);
    }

    @Override
    public Object vGet(String h) {
        return redisTemplate.opsForValue().get(h);
    }

    @Override
    public List<Object> vMultiGet(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void vSet(String h, String v) {
        redisTemplate.opsForValue().set(h, v);
    }

    @Override
    public long vIncre(String h, long incre) {
        return redisTemplate.opsForValue().increment(h, incre);
    }

    @Override
    public boolean setContain(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }



    @Override
    public List<Object> getByGroup(String groupKey) {
        return   redisTemplate.opsForHash().values(groupKey);
    }

}
