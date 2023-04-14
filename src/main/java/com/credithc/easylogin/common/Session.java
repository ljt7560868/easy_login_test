package com.credithc.easylogin.common;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Session {
    // 唯一标识符
    private String sessionId;
    // 创建时间
    private long creationTime;
    // 最后访问时间
    private long lastAccessTime;
    // 属性集合
    private Map<String, Object> attributes = new ConcurrentHashMap<>();
    // 最大空闲时间
    private int maxInactiveInterval;

    /**
     * 判断当前会话是否已过期
     */
    public boolean isExpired() {
        if (maxInactiveInterval > 0) {
            return System.currentTimeMillis() - lastAccessTime > maxInactiveInterval * 1000;
        } else {
            return false;
        }
    }

    /**
     * 获取属性值
     */
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    /**
     * 设置属性值
     */
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    /**
     * 删除属性值
     */
    public void removeAttribute(String name) {
        attributes.remove(name);
    }
}