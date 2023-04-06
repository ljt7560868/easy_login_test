package com.credithc.easylogin.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回码信息
     */
    private String message;
    /**
     * 实际数据
     */
    @SuppressWarnings({"squid:S1700"})
    private T result;

    public static <T> Result<T> ok() {
        return new Result<>(ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage().toUpperCase(), null);
    }

    public static <T> Result<T> ok(T result) {
        return new Result<>(ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage().toUpperCase(), result);
    }

    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> fail(BusinessErrorInterface e) {
        return new Result<>(e.getCode(), e.getMessage(), null);
    }
}