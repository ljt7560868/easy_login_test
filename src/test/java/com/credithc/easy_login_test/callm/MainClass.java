package com.credithc.easy_login_test.callm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: lvjietao
 * @CreateTime: 2023-08-16 15:56
 * @Description:
 */
public class MainClass {
    private static final int MAX_RETRY = 10; // 最大重试次数
    private static final int MAX_CONCURRENT_REQUESTS = 10; // 最大并发请求数

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable<Boolean>> tasks = new ArrayList<>();

        // 构造并发任务列表
        for (int i = 0; i < 10; i++) {
            tasks.add(() -> callThirdPartySystem());
        }

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENT_REQUESTS);
        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executorService);

        try {
            int retryCount = 0;
            while (retryCount <= MAX_RETRY) {
                List<Future<Boolean>> results = new ArrayList<>();

                // 提交并发任务
                for (int i = 0; i < tasks.size(); i++) {
                    if (retryCount > 0 && !results.get(i).get()) {
                        System.out.println("Reattempting task: " + (i+1));
                        // 重试失败的任务
                        results.set(i, completionService.submit(tasks.get(i)));
                    } else {
                        results.add(completionService.submit(tasks.get(i)));
                    }
                }

                boolean allTasksSuccessful = true;

                for (int i = 0; i < tasks.size(); i++) {
                    try {
                        Future<Boolean> result = results.get(i);
                        if (!result.get()) {
                            allTasksSuccessful = false;
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        // 处理异常情况，可根据需要进行补偿逻辑
                    }
                }

                if (allTasksSuccessful) {
                    // 所有任务都成功完成，跳出循环
                    break;
                } else {
                    // 重试
                    retryCount++;
                }
            }
        } finally {
            executorService.shutdown();
        }

        // 所有接口调用成功，触发后续逻辑
        if (executorService.isTerminated()) {
            processData();
        }
    }

    private static boolean callThirdPartySystem() {
        // 调用三方系统查询接口的具体实现逻辑
        // 返回 true 表示成功响应，返回 false 表示失败
        return true;
    }

    private static void processData() {
        // 处理接口返回的数据，触发后续逻辑
    }
}

