package com.wk.learning.thread.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingleTonEnum {

    private SingleTonEnum(){

    }

    private enum InstanceEnum {
        INSTANCE;

        private SingleTonEnum singleTonEnum;
        InstanceEnum(){
            singleTonEnum = new SingleTonEnum();
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < 10; i++) {
            Constructor<SingleTonEnum> constructor = SingleTonEnum.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingleTonEnum singleTonInnerClass = constructor.newInstance();
            System.out.println("反射的实例:" + singleTonInnerClass.hashCode());
            new Thread(() -> System.out.println(InstanceEnum.INSTANCE.hashCode())).start();
        }
    }

}
