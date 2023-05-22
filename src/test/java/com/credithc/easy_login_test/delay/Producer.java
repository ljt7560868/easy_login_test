package com.credithc.easy_login_test.delay;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class Producer implements Runnable {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final int id;
    private final BlockingQueue<DelayedTask> queue;
    private final CountDownLatch latch;

    public Producer(int id, BlockingQueue<DelayedTask> queue, CountDownLatch latch) {
        this.id = id;
        this.queue = queue;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            //for (int j = 0; j < 5; j++) {
            int j = 1;
            while (true) {
                DelayedTask task = new DelayedTask("Task " + id + "-" + j, 1000);
                queue.put(task);
                System.out.println("Produced " + task + " " + LocalDateTime.now().format(dtf));
                j++;
                // 模拟耗时操作
                Thread.sleep(1000);
            }
            //System.out.println("Producer " + id + " finished " + LocalDateTime.now().format(dtf));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown();
        }
    }


}
