package com.credithc.easylogin.security;

import com.credithc.easylogin.cache.RedisCacheHelp;
import com.credithc.infra.platform.ps.security.cache.ICacheAuthInfoService;
import com.credithc.infra.platform.ps.security.dto.CustomUserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: lvjietao
 * @CreateTime: 2022-10-31 18:37
 * @Description: 登陆用户redis缓存相关操作实现类
 */
@Component
public class AuthInfoServiceImpl implements ICacheAuthInfoService {

    @Resource
    private RedisCacheHelp redisCacheHelp;

    @Override
    public void saveLoginUserInfo(String userId, CustomUserDetails customUserDetails) {
        redisCacheHelp.saveLoginUserInfo(userId, customUserDetails);
    }

    @Override
    public CustomUserDetails getLoginUserInfo(String userId) {
        return redisCacheHelp.getLoginUserInfo(userId);
    }

    @Override
    public void delLoginUserInfo(String userId) {
        redisCacheHelp.delLoginUserInfo(userId);
    }

    @Override
    public void saveAppTokenData(String token) {
        redisCacheHelp.saveAppTokenData(token);
    }

    @Override
    public String getAppTokenData() {
        return redisCacheHelp.getAppTokenData();
    }
}
