package com.simple.classbytecode.bytebuddy.javaagent;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 16:35
 **/
public class AgentTest {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("hello bytebuddy agent");
            for (int i=0; i < 100L; i++) {

            }
        }, "javaagent").start();
    }

}
