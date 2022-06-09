package com.simple.classbytecode.bytebuddy.common.plugins.impl.rule;

import com.simple.classbytecode.bytebuddy.common.entity.AgentParam;
import com.simple.classbytecode.bytebuddy.common.util.AgentParamUtil;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.NameMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-10 00:15
 **/
public class DefaultRules {

    public static ElementMatcher.Junction<TypeDescription> defaultIgnoreClass() {
        return ElementMatchers.not(ElementMatchers.nameStartsWith("org.slf4j")
                .and(ElementMatchers.nameStartsWith("com.simple.classbytecode.bytebuddy.common"))
                .and(ElementMatchers.nameStartsWith("net.buddy"))
                .and(ElementMatchers.nameStartsWith("java.lang"))
        );
    }

    public static ElementMatcher.Junction<MethodDescription> defaultIgnoreMethod() {
        return ElementMatchers.not(ElementMatchers.named("main")
                .and(ElementMatchers.nameStartsWith("hashCode"))
                .and(ElementMatchers.nameStartsWith("toString"))
        );
    }

    public static ElementMatcher.Junction<TypeDescription> ignoreClassParam(AgentParam param) {
        List<String> strings = AgentParamUtil.dealParam(param.getIgnoreClassRule());
        return ElementMatchers.not(ElementMatchers.anyOf(strings));
    }

    public static ElementMatcher.Junction<TypeDescription> containClassParam(AgentParam param) {
        List<String> strings = AgentParamUtil.dealParam(param.getInterceptClassRule());
        List<ElementMatcher.Junction<TypeDescription>> values = new ArrayList<>();
        strings.forEach(s -> {
            values.add(ElementMatchers.nameStartsWith(s));
        });
        return ElementMatchers.anyOf(values);
    }

    public static ElementMatcher.Junction<MethodDescription> ignoreMethodParam(AgentParam param) {
        List<String> strings = AgentParamUtil.dealParam(param.getInterceptMethodRule());
        return ElementMatchers.not(ElementMatchers.anyOf(strings));
    }

    public static ElementMatcher.Junction<MethodDescription> containMethodParam(AgentParam param) {
        List<String> strings = AgentParamUtil.dealParam(param.getInterceptMethodRule());
        return ElementMatchers.anyOf(strings);
    }
}
