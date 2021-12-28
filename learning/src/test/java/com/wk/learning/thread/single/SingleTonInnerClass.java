package com.wk.learning.thread.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingleTonInnerClass {
    private  SingleTonInnerClass(){

    }

    public static SingleTonInnerClass getInstance() {
        return SingleTonStaticInnerClass.INSTANCE;
    }

    public static class SingleTonStaticInnerClass {
        private static SingleTonInnerClass INSTANCE = new SingleTonInnerClass();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < 10; i++) {
            Constructor<SingleTonInnerClass> constructor = SingleTonInnerClass.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingleTonInnerClass singleTonInnerClass = constructor.newInstance();
            System.out.println("反射的实例:" + singleTonInnerClass.hashCode());
            new Thread(() -> System.out.println(getInstance().hashCode())).start();
        }
    }
}
