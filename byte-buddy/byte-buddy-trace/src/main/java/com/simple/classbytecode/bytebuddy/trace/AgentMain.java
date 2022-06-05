package com.simple.classbytecode.bytebuddy.trace;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.isStatic;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:29
 **/
public class AgentMain {

    public static AgentBuilder.RawMatcher.ForElementMatchers ignorePrefixRules() {
        return new AgentBuilder.RawMatcher.ForElementMatchers(
                nameStartsWith("net.bytebuddy.")
                        .or(nameStartsWith("org.springframework."))
                        .or(nameStartsWith("java.lang."))
                        .or(nameStartsWith("com.simple.test.hello"))
        );
    }

    public static AgentBuilder.RawMatcher.ForElementMatchers ignoreMethods() {
        return new AgentBuilder.RawMatcher.ForElementMatchers(
                ElementMatchers.named("helloWorld")
                        .or(isStatic())
                        .or(ElementMatchers.named("hashCode"))
        );
    }

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("基于javaagent链路追踪");

        AgentBuilder agentBuilder = new AgentBuilder.Default();


        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            builder = builder.visit(
                    Advice.to(MethodAdvice.class)
                            .on(ElementMatchers.isMethod()
                                    .and(ElementMatchers.any())
                                    .and(ElementMatchers.not((ElementMatchers.named("hashCode"))))
                                    .and(ElementMatchers.not((ElementMatchers.named("Object"))))
                                    .and(ElementMatchers.not((ElementMatchers.named("toString"))))
                                    .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")))));
            return builder;
        };

        agentBuilder = agentBuilder
                .ignore(ignorePrefixRules())
                // 指定需要拦截的类 "com.simple.test.AgentTest"
                .type(ElementMatchers.nameStartsWith(agentArgs))
                .transform(transformer)
                .asDecorator();

        //监听
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
                System.out.println("onTransformation：" + typeDescription);
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

        };

        agentBuilder.with(listener).installOn(inst);

    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }
}
