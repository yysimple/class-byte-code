package com.simple.classbytecode.bytebuddy.javaagent;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 16:18
 **/
public class ByteBuddyAgent {

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        Class<?> bootStrapClass = Class.forName("com.simple.classbytecode.bytebuddy.javaagent.BootStrap", true, getClassLoader());
        Method preMain = bootStrapClass.getMethod("preMain", String.class, Instrumentation.class);
        preMain.invoke(null, agentArgs, inst);
    }

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        return ClassLoader.getSystemClassLoader();
    }

}
