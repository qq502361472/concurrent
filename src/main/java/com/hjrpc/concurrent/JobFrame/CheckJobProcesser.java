package com.hjrpc.concurrent.JobFrame;

import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class CheckJobProcesser {
    private CheckJobProcesser() {
        System.out.println("CheckJobProcesser构造");
    }

    private static class CheckJobProcesserHolder {
        private static CheckJobProcesser checkJobProcesser = new CheckJobProcesser();
    }

    public static CheckJobProcesser getInstance() {
        return CheckJobProcesserHolder.checkJobProcesser;
    }

    private static final DelayQueue<ItemVo<String>> delayQueue = new DelayQueue<>();

    static {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    ItemVo<String> take = delayQueue.take();
                    String jobName = take.getData();
                    ProgressTaskPool.getMap().remove(jobName);
                    System.out.println("---------------------"+jobName + " is out of time"+"-------------------------");
                    System.out.println("---------------------"+jobName + " is out of time"+"-------------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("启动过期守护线程");
    }

    public void putJob(String jobName, Long exprieTime) {
        System.out.println("["+jobName+"]"+"put into delayqueue ,exprie time:"+exprieTime+"ms!");
        delayQueue.offer(new ItemVo<String>(exprieTime, jobName));
    }

    class ItemVo<K> implements Delayed {

        private long activeTime;
        private K data;

        public ItemVo(long activeTime, K data) {
            this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS) + System.nanoTime();
            this.data = data;
        }

        public K getData(){
            return data;
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert((activeTime - System.nanoTime()), TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long v = activeTime - System.nanoTime() - o.getDelay(TimeUnit.NANOSECONDS);
            return v == 0 ? 0 : (v > 0 ? 1 : -1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ItemVo<?> itemVo = (ItemVo<?>) o;
            return Objects.equals(data, itemVo.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }

        @Override
        public String toString() {
            return "ItemVo{" +
                    "activeTime=" + activeTime +
                    ", data=" + data +
                    '}';
        }
    }
}
