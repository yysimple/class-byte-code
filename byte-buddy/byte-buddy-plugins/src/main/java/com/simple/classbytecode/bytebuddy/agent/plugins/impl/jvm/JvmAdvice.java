package com.simple.classbytecode.bytebuddy.agent.plugins.impl.jvm;

import net.bytebuddy.asm.Advice;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:48
 **/
public class JvmAdvice {

    @Advice.OnMethodExit()
    public static void exit() {
        JvmStack.printMemoryInfo();
        JvmStack.printGCInfo();
    }

}
