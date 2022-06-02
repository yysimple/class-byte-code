package com.simple.classbytecode.bytebuddy.javaagent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 16:20
 **/
public class BootStrap {

    public static void preMain(String args, Instrumentation inst) {
        AgentBuilder.Transformer transformer
                = (builder, typeDescription, classLoader, module)
                -> builder
                .visit(Advice.to(Interceptor.class)
                        .on(ElementMatchers.named("start")));

        new AgentBuilder.Default()
                .disableClassFormatChanges()
                .enableBootstrapInjection(inst, new File("/Users/chengxingwu/project/person-project/class-byte-code/byte-buddy/byte-buddy-javaagent/lib"))
                .ignore(new AgentBuilder.RawMatcher.ForElementMatchers(nameStartsWith("net.bytebuddy.")))
                .ignore(ElementMatchers.noneOf(Thread.class))
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                .with(AgentBuilder.RedefinitionStrategy.REDEFINITION)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .type(ElementMatchers.is(Thread.class))
                .transform(transformer)
                //.with(new MyAgentBuilderListener())
                .installOn(inst);
    }

    public static class Interceptor {

        @Advice.OnMethodEnter
        public static void enter(@Advice.This Thread thread, @Advice.Origin Method origin) {
            System.out.println("thread:" + thread.getName() + " enter thread timeMills:" + System.currentTimeMillis());
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            if (classLoader == null) {
                try {
                    origin.invoke(null, thread);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return;
            }

            try {
                Class reflectClass = Class.forName("com.simple.classbytecode.bytebuddy.javaagent.CustomThread", true, classLoader);
                Method start = reflectClass.getMethod("start", Thread.class);
                start.invoke(null, thread);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        @Advice.OnMethodExit
        public static void exit(@Advice.This Thread thread, @Advice.Origin Method origin) {
            System.out.println("thread:" + thread.getName() + " exit thread timeMills:" + System.currentTimeMillis());
        }
    }


}
