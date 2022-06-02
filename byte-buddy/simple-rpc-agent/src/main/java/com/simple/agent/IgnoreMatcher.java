package com.simple.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;

import static net.bytebuddy.matcher.ElementMatchers.isStatic;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 需要忽略的类型
 *
 * @author: WuChengXing
 * @create: 2022-06-02 17:27
 **/
public class IgnoreMatcher {

    public static AgentBuilder.RawMatcher.ForElementMatchers ignorePrefixRules() {
        return new AgentBuilder.RawMatcher.ForElementMatchers(
                nameStartsWith("net.bytebuddy.")
                        .or(nameStartsWith("org.springframework."))
                        .or(nameStartsWith("java.lang."))
        );
    }

    public static AgentBuilder.RawMatcher.ForElementMatchers ignoreMethods() {
        return new AgentBuilder.RawMatcher.ForElementMatchers(
                ElementMatchers.named("helloWorld")
                        .or(isStatic())
                        .or(ElementMatchers.named("hashCode"))
        );
    }
}
