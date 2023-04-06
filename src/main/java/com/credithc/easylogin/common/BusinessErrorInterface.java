package com.credithc.easylogin.common;

public interface BusinessErrorInterface {

    /**
     * 返回码
     *
     * @return 自定义状态码
     */
    int getCode();

    /**
     * 返回错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
