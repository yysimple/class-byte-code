package com.simple.classbytecode.bytebuddy.javaagent;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 16:35
 **/
public class CustomRunnable implements Runnable {

    Runnable delegate;

    public CustomRunnable(Runnable delegate) {
        this.delegate = delegate;
    }

    @Override
    public void run() {
        System.out.println("welcome to my custom thread");
        delegate.run();
    }
}
