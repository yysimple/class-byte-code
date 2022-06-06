package com.simple.classbytecode.bytebuddy.demo.agent.filter;

import net.bytebuddy.asm.Advice;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-06 21:27
 **/
public class MethodExistFilter {

    @Advice.OnMethodEnter(skipOn = Advice.OnDefaultValue.class)
    public static int enter() {
        System.out.println("=== 方法进入 ===");
        return 0;
    }
}
