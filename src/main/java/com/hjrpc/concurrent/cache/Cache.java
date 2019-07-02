package com.hjrpc.concurrent.cache;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Cache<K, V> {

    private ConcurrentHashMap<K, V> dataMap = new ConcurrentHashMap<>();

    private DelayQueue<ItemVo<K>> queue = new DelayQueue<ItemVo<K>>();

    public Cache() {
        Thread thread = new Thread(() -> {
            for (; ; ) {
                try {
                    ItemVo<K> key = queue.take();
                    System.out.println("元素："+key+"过期");
                    System.out.println("删除元素："+key.getData()+dataMap.get(key.getData()));
                    dataMap.remove(key.getData());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public synchronized void  put(K k,V v,Long expireTime){
        V value = dataMap.put(k, v);
        if(expireTime==null){
            return;
        }

        ItemVo<K> kItemVo = new ItemVo<>(expireTime, k);
        if(value!=null){
            queue.remove(kItemVo);
        }

        queue.put(kItemVo);
    }

    public V get(K k){
        return dataMap.get(k);
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
