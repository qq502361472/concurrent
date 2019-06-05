package com.hjrpc.concurrent.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ReentrantLockExpress <br/>
 * Description: <br/>
 * date: 2019/6/3 16:39<br/>
 *
 * @author HuJian<br               />
 * @since JDK 1.8
 */
public class ReentrantLockExpress {
    private static int distance = 0;
    private static String place = "BeiJing";

    private static ReentrantLock lock = new ReentrantLock();
//    private static ReentrantLock distanceLock = new ReentrantLock();
//    private static ReentrantLock placeLock = new ReentrantLock();

//    private static Condition distanceCondition = lock.newCondition();
//    private static Condition placeCondition = lock.newCondition();
    private static Condition condition = lock.newCondition();
//    private static Condition distanceCondition = distanceLock.newCondition();
//    private static Condition placeCondition = placeLock.newCondition();

    public ReentrantLockExpress(int distance, String place) {
        ReentrantLockExpress.distance = distance;
        ReentrantLockExpress.place = place;
    }


    public void changeDistance(int distance) {
//        distanceLock.lock();
        lock.lock();
        try {
            ReentrantLockExpress.distance = distance;
            System.out.println("changeDistance use signal");
//            distanceCondition.signal();
            condition.signal();
        } finally {
//            distanceLock.unlock();
            lock.unlock();
        }
    }

    public void changePlace(String place) {
//        placeLock.lock();
        lock.lock();
        try {
            ReentrantLockExpress.place = place;
            System.out.println("changePlace use signalAll");
//            placeCondition.signalAll();
            condition.signalAll();
        } finally {
//            placeLock.unlock();
            lock.unlock();
        }
    }


    public void watchDistance() throws InterruptedException {
//        distanceLock.lock();
        lock.lock();
        try {
//            distanceCondition.await();
            condition.await();
            System.out.println(Thread.currentThread().getName() + ":distance change to " + distance);
        } finally {
//            distanceLock.unlock();
            lock.unlock();
        }
    }

    public void watchPlace() throws InterruptedException {
//        placeLock.lock();
        lock.lock();
        try {
//            placeCondition.await();
            condition.await();
            System.out.println(Thread.currentThread().getName() + ":place change to " + place);
        } finally {
//            placeLock.unlock();
            lock.unlock();
        }
    }
}
