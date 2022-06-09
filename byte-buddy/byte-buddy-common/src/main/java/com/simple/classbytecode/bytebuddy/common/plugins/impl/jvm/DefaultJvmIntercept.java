package com.simple.classbytecode.bytebuddy.common.plugins.impl.jvm;

import com.simple.classbytecode.bytebuddy.common.entity.AgentParam;
import com.simple.classbytecode.bytebuddy.common.plugins.InterceptPoint;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 默认的jvm拦截器
 *
 * @author: WuChengXing
 * @create: 2022-06-09 23:47
 **/
public class DefaultJvmIntercept implements InterceptPoint {

    @Override
    public ElementMatcher<TypeDescription> buildTypesMatcher(AgentParam agentParam) {
        return null;
    }

    @Override
    public ElementMatcher<MethodDescription> buildMethodsMatcher(AgentParam agentParam) {
        return null;
    }
}
