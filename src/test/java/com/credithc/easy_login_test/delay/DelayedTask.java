package com.credithc.easy_login_test.delay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String name;
    private long expireTime;
    private boolean blocked;
    private long blockedTime;
    private boolean completed;

    public DelayedTask(String name, long delayMs) {
        this.name = name;
        this.expireTime = System.currentTimeMillis() + delayMs;
        this.blocked = false;
        this.blockedTime = delayMs;
        this.completed = false;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = expireTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) {
            return 0;
        }
        long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(diff, 0L);
        /*if (o instanceof DelayedTask) {
            DelayedTask other = (DelayedTask) o;
            long diff = getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS);
            return Long.compare(diff, 0L);
        }
        long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return diff > 0 ? 1 : (diff < 0 ? -1 : 0);*/
    }

    public String getName() {
        return name;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public long getBlockedTime() {
        return blockedTime;
    }

    public void setBlockedTime(long blockedTime) {
        this.expireTime += blockedTime;
        this.blockedTime = blockedTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "name='" + name + '\'' +
                ", expireTime=" + SDF.format(new Date(expireTime)) +
                ", blocked=" + blocked +
                ", blockedTime=" + blockedTime +
                ", completed=" + completed +
                '}';
    }

}