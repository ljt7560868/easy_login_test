package com.credithc.easylogin.producer;

import com.alibaba.fastjson.JSON;
import com.credithc.easylogin.DTO.LoginUser;
import com.credithc.idg.rabbit.spring.boot.properties.AmqpOptionsProducerProperties;
import com.credithc.idg.rabbit.spring.boot.supports.AbstractMessageProducer;
import org.springframework.stereotype.Component;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-06-05 14:11
 * @Description:
 */
@Component
public class TestProducer extends AbstractMessageProducer<LoginUser> {

    @Override
    protected AmqpOptionsProducerProperties getAmqpProducerProperties() {
        return getAmqpProducerProperties("send2business");
    }

    @Override
    protected String getJsonMessage(LoginUser loginUser) {
        return JSON.toJSONString(loginUser);
    }
}
