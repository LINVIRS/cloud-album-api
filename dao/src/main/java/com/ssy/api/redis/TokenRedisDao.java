package com.ssy.api.redis;

import com.ssy.api.redis.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author D丶Cheng
 * @Created with IntelliJ IDEA.
 * @date: 2018/7/4
 *
 */

@Component
public class TokenRedisDao {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void saveToken(String prefix,Integer userId, String token) {

        redisTemplate.opsForValue().set(prefix + userId, token);
        redisTemplate.expire(prefix + userId, RedisConstant.APP_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);

    }


    public void removeToken(String prefix,Integer userId) {

        redisTemplate.delete(prefix + userId);

    }

    public boolean checkToken(String prefix,Integer userId, String token) {
        String originToken = redisTemplate.opsForValue().get(prefix + userId);

        return originToken != null && originToken.equals(token);

    }


}
