package com.credithc.easylogin.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-07-20 11:39
 * @Description: 测试job1
 */
@Slf4j
@Component
public class TestJob1Handler {

    @XxlJob("testJob1Handler")
    public void testJob1HandlerExecute() throws Exception {
        log.info("execute, testJob1Handler start");
        // 任务日志打印示例
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        log.info("execute, testJob1Handler end");
    }
}
