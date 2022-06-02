package com.simple.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 入口
 *
 * @author: WuChengXing
 * @create: 2022-06-02 16:43
 **/
public class AgentMain {

    /**
     * JVM 首先尝试在代理类上调用以下方法
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            return builder
                    // 拦截任意方法
                    .method(ElementMatchers.any())
                    // 委托
                    .intercept(MethodDelegation.to(MonitorMethod.class));
        };

        new AgentBuilder
                .Default()
                // org.springframework.
                .ignore(IgnoreMatcher.ignorePrefixRules())
                .ignore(IgnoreMatcher.ignoreMethods())
                // 指定需要拦截的类 "com.simple.test.AgentTest"
                .type(ElementMatchers.nameStartsWith(agentArgs))
                .transform(transformer)
                .installOn(inst);
    }

    /**
     * 如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
     *
     * @param agentArgs
     */
    public static void premain(String agentArgs) {
    }
}
