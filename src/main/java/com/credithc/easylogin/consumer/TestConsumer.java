package com.credithc.easylogin.consumer;

import com.credithc.idg.rabbit.spring.boot.supports.BaseConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-05-26 09:40
 * @Description: 测试mq消费
 */
@Slf4j
@Service
public class TestConsumer extends BaseConsumer {

    @Override
    public void doProcess(String message) {
    }
}
