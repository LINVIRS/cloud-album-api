package com.ssy.api.SQLservice.vo;


import com.ssy.api.redis.constant.RedisConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Description:  APP 登录后的token对象
 * @Date: 2018/7/5 下午8:31
 */

public class UserTokenVO {


    public UserTokenVO() {
    }

    public UserTokenVO(Integer id) {
        this.id = id;
        this.loginAt = System.currentTimeMillis();
        this.expires = RedisConstant.APP_TOKEN_EXPIRE_TIME;
    }


    private long expires;

    private long loginAt;

    private Integer id;

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public long getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(long loginAt) {
        this.loginAt = loginAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map toMap(){
        Map map = new HashMap();
        map.put("userId", this.id);
        map.put("expires", this.expires);
        map.put("loginAt", this.loginAt);
        return map;
    }

    public UserTokenVO fromMap(Map map){

        UserTokenVO userToken = new UserTokenVO();
        userToken.expires = Long.parseLong(String.valueOf(map.get("expires")));
        userToken.id = Integer.parseInt(String.valueOf(map.get("userId")));
        userToken.loginAt = Long.parseLong(String.valueOf(map.get("loginAt")));

        return userToken;

    }

}
