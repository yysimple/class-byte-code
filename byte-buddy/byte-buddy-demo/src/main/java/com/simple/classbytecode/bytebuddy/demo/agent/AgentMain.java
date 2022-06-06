package com.simple.classbytecode.bytebuddy.demo.agent;

import com.simple.classbytecode.bytebuddy.demo.agent.filter.MethodEnterFilter;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: agent测试主类
 *
 * @author: WuChengXing
 * @create: 2022-06-06 21:14
 **/
public class AgentMain {

    public void defineNewClass() throws Exception {
        // 构建一个新类
        Class<?> buildClazz = new ByteBuddy()
                .redefine(CommonTestClazz.class)
                .visit(Advice.to(MethodEnterFilter.class).on(ElementMatchers.not(ElementMatchers.isConstructor())))
                .make()
                .load(ClassLoadingStrategy.BOOTSTRAP_LOADER, ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        // 调用构造器
        Object o = buildClazz.getDeclaredConstructor().newInstance();
        buildClazz.getMethod("testVoid").invoke(o);
    }

    public static void main(String[] args) throws Exception {
        AgentMain agentMain = new AgentMain();
        agentMain.defineNewClass();
    }
}
