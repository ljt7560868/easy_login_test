package com.credithc.easy_login_test.delay;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * 每个任务的执行过程如下：
 * <p>
 * 1. 创建了 5 个生产者线程和 10 个消费者线程，使用 CountDownLatch 来协调线程之间的同步。
 * <p>
 * 2. 生产者线程向延迟队列中添加了 50 个任务（每个生产者线程添加 5 个任务），并在每次添加任务后模拟一段耗时操作。
 * <p>
 * 3. 消费者线程不断从延迟队列中取出任务并处理。如果当前任务已经完成，则跳过；否则调用三方接口获取数据，如果返回为空则重新放入队列并增加阻塞等待时间，否则打印输出数据并设置任务状态为已完成。
 * <p>
 * 4. 当所有生产者线程都运行结束且队列为空时，退出程序。
 */
public class DelayedProducerConsumerExample {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int NUM_PRODUCERS = 2;
    private static final int NUM_CONSUMERS = 6;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DelayedTask> queue = new DelayQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // 生产者线程
        CountDownLatch producersLatch = new CountDownLatch(NUM_PRODUCERS);
        for (int i = 0; i < NUM_PRODUCERS; i++) {
            Producer producer = new Producer(i + 1, queue, producersLatch);
            executor.execute(producer);
        }

        // 消费者线程
        CountDownLatch consumersLatch = new CountDownLatch(NUM_CONSUMERS);
        for (int i = 0; i < NUM_CONSUMERS; i++) {
            Consumer consumer = new Consumer(queue, consumersLatch, producersLatch);
            executor.execute(consumer);
        }

        // 等待生产者线程运行结束
        producersLatch.await();

        while (consumersLatch.getCount() > 0 || !queue.isEmpty()) {
            Thread.sleep(1000);
        }
        System.out.println("All tasks completed " + LocalDateTime.now().format(dtf));

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}