package com.credithc.easylogin.util;

import com.credithc.easylogin.DTO.LoginUser;
import org.springframework.core.NamedThreadLocal;

import java.lang.reflect.Method;

public final class ThreadLocalUtil {

    private ThreadLocalUtil() {

    }

    private static final NamedThreadLocal<Long> TIME_THREAD_LOCAL = new NamedThreadLocal<>("TIME");

    private static final NamedThreadLocal<Method> METHOD_NAMED_THREAD_LOCAL = new NamedThreadLocal<>("METHOD");

    private static final NamedThreadLocal<LoginUser> USER_THREAD_LOCAL = new NamedThreadLocal<>("USER");

    private static final NamedThreadLocal<Object> THREAD_LOCAL = new NamedThreadLocal<>("THREAD");

    private static final NamedThreadLocal<String> CAPTCHA_THREAD_LOCAL = new NamedThreadLocal<>("CAPTCHA");

    /**
     * 存放当前方法
     *
     * @param method
     */
    public static void putCurrentMethod(Method method) {
        METHOD_NAMED_THREAD_LOCAL.set(method);
    }

    /**
     * 获取当前方法
     */
    public static Method getCurrentMethod() {
        return METHOD_NAMED_THREAD_LOCAL.get();
    }

    /**
     * 删除当前方法
     */
    public static void removeCurrentMethod() {
        METHOD_NAMED_THREAD_LOCAL.remove();
    }

    /**
     * 存放时间
     *
     * @param time
     */
    public static void putTime(long time) {
        TIME_THREAD_LOCAL.set(time);
    }

    /**
     * 获取时间
     */
    public static long getTime() {
        return TIME_THREAD_LOCAL.get();
    }

    /**
     * 删除时间
     */
    public static void removeTime() {
        TIME_THREAD_LOCAL.remove();
    }

    /**
     * 存放登录用户
     *
     * @param user
     */
    public static void putUser(LoginUser user) {
        USER_THREAD_LOCAL.set(user);
    }

    /**
     * 获取登录用户
     */
    public static LoginUser getUser() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 删除登录用户
     */
    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }

    /**
     * 存放验证码
     *
     * @param captcha
     */
    public static void putCaptcha(String captcha) {
        CAPTCHA_THREAD_LOCAL.set(captcha);
    }

    /**
     * 获取验证码
     */
    public static String getCaptcha() {
        return CAPTCHA_THREAD_LOCAL.get();
    }

    /**
     * 删除验证码
     */
    public static void removeCaptcha() {
        CAPTCHA_THREAD_LOCAL.remove();
    }

    /**
     * 存放当前线程中临时性数据，注意：此方法可能存在多个逻辑共用同一thread的情况，请确认数据的串行化
     */
    public static void putObject(Object param) {
        THREAD_LOCAL.set(param);
    }

    /**
     * 获取存放在当前线程中临时性数据，注意：此方法可能存在多个逻辑共用同一thread的情况，请确认数据的串行化
     */
    public static Object getObject() {
        return THREAD_LOCAL.get();
    }

    public static void removeObject() {
        THREAD_LOCAL.remove();
    }
}
