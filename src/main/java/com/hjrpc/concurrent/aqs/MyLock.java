package com.hjrpc.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock implements Lock {

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    class Sync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            int s = getState();
            Thread currentThread = Thread.currentThread();
            if(s ==0){
                if(compareAndSetState(0,1)){
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            }else if(getExclusiveOwnerThread()== currentThread){
                if(s <0){
                    throw new RuntimeException("代码异常，状态小于零");
                }
                setState(s +arg);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(Thread.currentThread()!=getExclusiveOwnerThread()){
                throw new RuntimeException("我都没拿到锁，怎么释放啊？？？？");
            }
            int s = getState() -arg;

            if(s<0){
                throw new RuntimeException("兄弟你代码写错了吧,怎么释放的比拿的多呢");
            }
            if(s==0) {
                setExclusiveOwnerThread(null);
            }
            setState(s);
            return true;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        @Override
        protected boolean isHeldExclusively() {

            return getExclusiveOwnerThread()==Thread.currentThread();
        }
    }
}
