package com.credithc.easy_login_test.delay;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Consumer implements Runnable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final long MAX_BLOCK_TIME = 120000L;

    private final BlockingQueue<DelayedTask> queue;
    private final CountDownLatch consumerCountDownLatch;
    private final CountDownLatch producerCountDownLatch;

    public Consumer(BlockingQueue<DelayedTask> queue, CountDownLatch consumerCountDownLatch,CountDownLatch producerCountDownLatch) {
        this.queue = queue;
        this.consumerCountDownLatch = consumerCountDownLatch;
        this.producerCountDownLatch = producerCountDownLatch;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName() + " queued data " + LocalDateTime.now().format(dtf));
                DelayedTask task = queue.take();
                System.out.println(Thread.currentThread().getName() + " queued data " + task + LocalDateTime.now().format(dtf));
                if (!task.isCompleted()) {
                    String data = ThirdPartyApi.callThirdPartyApi(task.getName());
                    if (data != null) {
                        System.out.println(Thread.currentThread().getName() + " 成功处理完成 " + data + " " + LocalDateTime.now().format(dtf));
                        task.setCompleted(true);
                    } else {
                        long blockedTime = Math.min(task.getBlockedTime() * 2, MAX_BLOCK_TIME);
                        if (blockedTime == MAX_BLOCK_TIME) {
                            System.out.println(Thread.currentThread().getName() + " Exceeded maximum block time: " + task + " " + LocalDateTime.now().format(dtf));
                            task.setBlocked(false);
                            task.setCompleted(true);
                        } else {
                            task.setBlocked(true);
                            task.setBlockedTime(blockedTime);
                            queue.put(task);
                            System.out.println(Thread.currentThread().getName() + " 重新入队 " + task + " " + LocalDateTime.now().format(dtf));
                        }
                    }
                }
                System.out.println("队列还有：" + queue.size());
                if (queue.isEmpty()) {
                    System.out.println("队列空了！");
                }
                if (queue.isEmpty() && producerCountDownLatch.getCount() == 0) {
                    System.out.println("队列空了！且生产者生产完了！");
                    //break;
                } /*else if (task.isBlocked()) {
                    System.out.println("数据阻塞！");
                    // 阻塞等待
                    Thread.sleep(task.getBlockedTime());
                }*/
            }
            System.out.println(Thread.currentThread().getName() + " Consumer finished " + LocalDateTime.now().format(dtf));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            consumerCountDownLatch.countDown();
        }
    }
}
