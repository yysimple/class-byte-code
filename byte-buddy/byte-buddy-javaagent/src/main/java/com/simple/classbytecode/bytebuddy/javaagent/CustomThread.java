package com.simple.classbytecode.bytebuddy.javaagent;

import java.lang.reflect.Field;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 16:34
 **/
public class CustomThread {

    public static void start(Thread thread) {
        try {
            System.out.println("welcome to my custom thread");
            Field field = Thread.class.getDeclaredField("target");
            field.setAccessible(true);
            Object targetValue = field.get(thread);
            if (targetValue instanceof Runnable) {
                Runnable runnable = (Runnable) targetValue;
                field.set(thread, new CustomRunnable(runnable));
            }
        } catch (Throwable e) {
            System.out.println(e);
            ;
        }
    }
}
