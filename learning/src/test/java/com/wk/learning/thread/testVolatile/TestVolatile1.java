package com.wk.learning.thread.testVolatile;

import java.util.concurrent.TimeUnit;

/**
 * 可见性测试.
 */
public class TestVolatile1 {
    private volatile static Integer num = 0;
    public static void main(String[] args) {
        new Thread(() ->{
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num ++;
        System.out.println(num);
    }

}
