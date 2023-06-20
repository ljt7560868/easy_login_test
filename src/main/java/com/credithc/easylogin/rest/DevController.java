package com.credithc.easylogin.rest;

import com.alibaba.fastjson.JSON;
import com.credithc.easylogin.DTO.LoginUser;
import com.credithc.easylogin.producer.TestProducer;
import com.credithc.easylogin.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-06-05 14:24
 * @Description:
 */
@RestController
@RequestMapping("/dev")
@Slf4j
public class DevController {


    @Resource
    private TestProducer testProducer;

    @GetMapping("/syncSendMessage")
    public void syncSendMessage() {
        log.info("syncSendMessage--------");
        LoginUser user = ThreadLocalUtil.getUser();
        boolean result = testProducer.syncSendMessage(JSON.toJSONString(user));
        log.info("syncSendMessage,result:{}", result);
    }

    @GetMapping("/sendMessage")
    public void sendMessage() {
        LoginUser user = ThreadLocalUtil.getUser();
        testProducer.sendMessage(user);
    }
}
