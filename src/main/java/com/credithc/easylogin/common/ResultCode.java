package com.credithc.easylogin.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public enum ResultCode implements BusinessErrorInterface {

    /**
     * success
     */
    SUCCESS(200, "SUCCESS"),

    /**
     * 账号或密码错误
     */
    EC_400_ERROR(400, "账号或密码错误"),

    /**
     * 未授权, 访问受限
     */
    EC_401_ERROR(401, "未授权, 访问受限"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(-1, "system.error"),
    ;

    private int code;
    @Setter
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
