package com.hjrpc.concurrent.Semaphore;

import com.hjrpc.concurrent.Pool.MyConnection;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SemaphorePool <br/>
 * Description: <br/>
 * date: 2019/6/3 12:16<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class SemaphorePool {


    private static LinkedList<Connection> pool = new LinkedList<Connection>();
    private static Semaphore usefullCon,useLessCon;

    public SemaphorePool(int initsize) {
        if (initsize <= 0) {
            return;
        }
        for (int i = 0; i < initsize; i++) {
            pool.addFirst(MyConnection.fetchConnection());
        }

        usefullCon = new Semaphore(initsize);
        useLessCon = new Semaphore(0);
    }

    public Connection getConnection(long timeOut) {
        if(timeOut<=0){
            try {
                usefullCon.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            try {
                if (!usefullCon.tryAcquire(timeOut, TimeUnit.MILLISECONDS)) {
                    return null;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection connection ;
        synchronized (pool){
            connection = pool.removeFirst();
        }
        useLessCon.release();
        return connection;
    }

    public void relaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            useLessCon.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (pool) {
            pool.addLast(connection);
        }
        usefullCon.release();
    }
}
