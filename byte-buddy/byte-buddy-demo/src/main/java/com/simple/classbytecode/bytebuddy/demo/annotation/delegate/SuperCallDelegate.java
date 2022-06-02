package com.simple.classbytecode.bytebuddy.demo.annotation.delegate;

import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 14:57
 **/
public class SuperCallDelegate {

    public static String normal(String var1, @SuperCall Callable<String> superCall) {
        try {
            String call = superCall.call();
            return "SuperCall: " + call;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("拦截SuperCall！！");
        }
    }
}
