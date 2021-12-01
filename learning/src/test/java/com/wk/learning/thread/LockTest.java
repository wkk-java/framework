package com.wk.learning.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    class ProductInfo {
        private Integer sales = 0;
        private Integer repertory = 10;

        public void sale() {
            repertory--;
            sales++;
        }


    }
}
