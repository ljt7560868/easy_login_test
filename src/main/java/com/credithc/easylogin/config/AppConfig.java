package com.credithc.easylogin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-04-20 11:24
 * @Description: 配置信息
 */
@Component
@ConfigurationProperties(prefix = "login")
@Data
public class AppConfig {

    // 用户信息
    public Map<String,String> userInfoMap;

    private String token;

}


