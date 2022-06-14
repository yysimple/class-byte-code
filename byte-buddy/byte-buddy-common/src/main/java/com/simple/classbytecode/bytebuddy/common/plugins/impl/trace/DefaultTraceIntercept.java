package com.simple.classbytecode.bytebuddy.common.plugins.impl.trace;

import com.simple.classbytecode.bytebuddy.common.entity.AgentParam;
import com.simple.classbytecode.bytebuddy.common.plugins.InterceptPoint;
import com.simple.classbytecode.bytebuddy.common.plugins.impl.rule.DefaultRules;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 默认的trace拦截规则
 *
 * @author: WuChengXing
 * @create: 2022-06-09 23:48
 **/
public class DefaultTraceIntercept implements InterceptPoint {
    @Override
    public ElementMatcher<TypeDescription> buildTypesMatcher(AgentParam agentParam) {
        return ElementMatchers.nameStartsWith("com.simple.test");
    }

    @Override
    public ElementMatcher<MethodDescription> buildMethodsMatcher(AgentParam agentParam) {
        return ElementMatchers.isMethod()
                .and(ElementMatchers.any())
                .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")));
    }
}
