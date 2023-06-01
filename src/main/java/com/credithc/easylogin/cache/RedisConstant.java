package com.credithc.easylogin.cache;

/**
 * @Author: lvjietao
 * @CreateTime: 2022-09-16 15:57
 * @Description: redis常量类
 */
public class RedisConstant {

    /**
     * 系统全局前缀
     */
    public static final String SYS_PREFIX = "easy.login.test.";

    /**
     * 登陆用户信息缓存失效时间
     */
    public final static Long EXPIRE_LOGIN_USER_INFO = 60 * 60 * 2L - 600;

    /**
     * refreshToken缓存失效时间
     */
    public final static Long EXPIRE_REFRESH_TOKEN = 60 * 60 * 12L - 600;


    public final static Long EXPIRE_ONE_DAY = 60 * 60 * 24L;

    public final static Long EXPIRE_30_DAY = 60 * 60 * 24 * 30L;

    /**
     * 登陆用户信息缓存key
     */
    public static String generateLoginUserInfoCacheKey(String token) {
        return String.format(SYS_PREFIX + "login_user_info_key:%s", token);
    }

    /**
     * token缓存关系key
     */
    public static String generateTokenDataCacheKey(String token) {
        return String.format(SYS_PREFIX + "token_data_key.%s", token);
    }

    /**
     * token缓存关系key
     */
    public static String generateAppTokenCacheKey() {
        return SYS_PREFIX + "app_token_key";
    }

    /**
     * 订单当前操作信息缓存key
     */
    public static String generateCurOrderOptInfoCacheKey(String orderNo) {
        return String.format(SYS_PREFIX + "cur_order_opt_info.%s", orderNo);
    }

    /**
     * 发送mq消息缓存key
     *
     * @param orderNo
     * @param producerBusinessType
     * @return
     */
    public static String generateSendMqMsgCacheKey(String orderNo, String sourceNo, Integer producerBusinessType) {
        return String.format(SYS_PREFIX + "send_mq_msg.%s_%s_%s", orderNo, sourceNo, producerBusinessType);
    }
}
