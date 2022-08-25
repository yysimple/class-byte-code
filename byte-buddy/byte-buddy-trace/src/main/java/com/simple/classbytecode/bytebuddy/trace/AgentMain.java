package com.simple.classbytecode.bytebuddy.trace;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static ElementMatcher.Junction<TypeDescription> test() {
        List<String> strings = Arrays.asList("com.simple.test");
        List<ElementMatcher.Junction<TypeDescription>> values = new ArrayList<>();
        final ElementMatcher.Junction<TypeDescription>[] junction = new ElementMatcher.Junction[]{ElementMatchers.nameStartsWith("")};
        strings.forEach(s -> {
            junction[0] = junction[0].and(nameStartsWith(s));
        });
        return ElementMatchers.anyOf(junction[0]);
    }

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("基于javaagent链路追踪");

        AgentBuilder agentBuilder = new AgentBuilder.Default();

        agentBuilder.ignore(ignorePrefixRules())
                .type(ElementMatchers.nameStartsWith("com.simple.test"))
                .transform(new Transformer())
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                .with(new Listener())
                .installOn(inst);

    }

    private static class Transformer implements AgentBuilder.Transformer {
        @Override
        public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
            return builder.visit(
                    Advice.to(MethodAdvice.class)
                            .on(ElementMatchers.isMethod()
                                    .and(ElementMatchers.any()).and(ElementMatchers.not(
                                            ElementMatchers.named("hashCode").or(ElementMatchers.named("toString"))
                                    ))
                            ));
        }
    }

    private static class Listener implements AgentBuilder.Listener {

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
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }
}
