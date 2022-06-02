package com.simple.classbytecode.bytebuddy.jvm;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: agent入口
 *
 * @author: WuChengXing
 * @create: 2022-06-02 23:59
 **/
public class AgentMain {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is my agent：" + agentArgs);

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                JvmStack.printMemoryInfo();
                JvmStack.printGCInfo();
                System.out.println("===================================================================================================");
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }
}
