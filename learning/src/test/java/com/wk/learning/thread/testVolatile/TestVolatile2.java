package com.wk.learning.thread.testVolatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile是否原子操作测试.
 */
public class TestVolatile2 {
//    private volatile static int num = 0;
    private volatile static AtomicInteger num = new AtomicInteger(0);
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
//                    num ++;
                    num.getAndIncrement();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(num);
    }
}
