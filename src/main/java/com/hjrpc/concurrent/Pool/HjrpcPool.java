package com.hjrpc.concurrent.Pool;

import java.sql.Connection;
import java.util.LinkedList;

public class HjrpcPool {

    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    public HjrpcPool(int initsize) {
        if (initsize <= 0) {
            return;
        }
        for (int i = 0; i < initsize; i++) {
            pool.addFirst(MyConnection.fetchConnection());
        }
    }

    public Connection getConnection(long timeOut)  {
        synchronized (pool) {
            if (timeOut < 0) {
                if (pool.isEmpty()) {
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Connection con = null;
                if(!pool.isEmpty()) {
                    con= pool.removeFirst();
                }
                return con;
            } else {
                long overTime = System.currentTimeMillis() + timeOut;
                long remainTime = timeOut;
                while (pool.isEmpty() && remainTime > 0) {
                    try {
                        pool.wait(remainTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    remainTime = overTime - System.currentTimeMillis();
                }
                Connection con = null;
                if (!pool.isEmpty()) {
                    con = pool.removeFirst();
                }
                return con;
            }
        }
    }

    public void relaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        synchronized (pool) {
            pool.addLast(connection);
            pool.notifyAll();
        }
    }
}
