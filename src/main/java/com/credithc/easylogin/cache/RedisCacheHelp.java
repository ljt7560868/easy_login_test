package com.credithc.easylogin.cache;

import com.alibaba.fastjson.JSON;
import com.credithc.infra.platform.ps.security.dto.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: lvjietao
 * @CreateTime: 2022-09-16 15:56
 * @Description: redis缓存操作帮助类
 */
@Slf4j
@Component
public class RedisCacheHelp {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 保存登陆用户信息
     *
     * @param userId
     * @param userDetail
     */
    public void saveLoginUserInfo(String userId, CustomUserDetails userDetail) {
        String userInfoCacheKey = RedisConstant.generateLoginUserInfoCacheKey(userId);
        redisTemplate.opsForValue().set(userInfoCacheKey, JSON.toJSONString(userDetail), RedisConstant.EXPIRE_LOGIN_USER_INFO);
    }

    /**
     * 获取登陆用户缓存信息
     *
     * @param userId
     * @return
     */
    public CustomUserDetails getLoginUserInfo(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        String userInfoCacheKey = RedisConstant.generateLoginUserInfoCacheKey(userId);
        String userInfoStr = (String) redisTemplate.opsForValue().get(userInfoCacheKey);
        return StringUtils.isBlank(userInfoStr) ? null : JSON.parseObject(userInfoStr, CustomUserDetails.class);
    }

    /**
     * 删除登陆用户缓存信息
     *
     * @param token
     */
    public void delLoginUserInfo(String token) {
        String userInfoCacheKey = RedisConstant.generateLoginUserInfoCacheKey(token);
        redisTemplate.delete(userInfoCacheKey);
    }

    /**
     * 保存appToken数据
     *
     * @param token
     */
    public void saveAppTokenData(String token) {
        String cacheKey = RedisConstant.generateAppTokenCacheKey();
        redisTemplate.opsForValue().set(cacheKey, token, RedisConstant.EXPIRE_LOGIN_USER_INFO);
    }

    /**
     * 获取appToken数据
     *
     * @return
     */
    public String getAppTokenData() {
        String cacheKey = RedisConstant.generateAppTokenCacheKey();
        return (String) redisTemplate.opsForValue().get(cacheKey);
    }


}
