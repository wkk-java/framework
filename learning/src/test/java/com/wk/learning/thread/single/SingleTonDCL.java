package com.wk.learning.thread.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingleTonDCL {

    private volatile static SingleTonDCL instance;
    private static boolean hasLock = false;

    private SingleTonDCL() {
        //防止反射
        synchronized (SingleTonDCL.class) {
            if (!hasLock) {
                hasLock = true;
            } else {
                throw new RuntimeException("不允许反射拿到实例");
            }
        }
    }

    public static SingleTonDCL getInstance() {
        if (instance == null) {
            hasLock = true;
            synchronized (SingleTonDCL.class) {
                if (instance == null) {
                    instance = new SingleTonDCL();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(getInstance().hashCode())).start();
//            Constructor<SingleTonDCL> constructor = SingleTonDCL.class.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            SingleTonDCL singleTonDCL = constructor.newInstance();
//            System.out.println("反射的实例:" + singleTonDCL.hashCode());
        }
    }
}
