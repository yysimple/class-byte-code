package com.simple.classbytecode.bytebuddy.common.plugins.impl.trace;

import com.simple.classbytecode.bytebuddy.common.constant.AgentConstant;
import com.simple.classbytecode.bytebuddy.common.entity.AgentParam;
import com.simple.classbytecode.bytebuddy.common.plugins.IPlugin;
import com.simple.classbytecode.bytebuddy.common.plugins.InterceptPoint;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:48
 **/
public class TracePlugin implements IPlugin {

    @Override
    public String name() {
        return AgentConstant.PLUGIN_TRACE;
    }

    @Override
    public List<InterceptPoint> buildInterceptPoint() {
        List<InterceptPoint> interceptPoints = new ArrayList<>();
        interceptPoints.add(new DefaultTraceIntercept());
//        InterceptPoint[] interceptPoints = new InterceptPoint[1];
//        interceptPoints[0] = new DefaultTraceIntercept();
        return interceptPoints;
//        return new InterceptPoint[] {
//                new InterceptPoint() {
//                    @Override
//                    public ElementMatcher<TypeDescription> buildTypesMatcher(AgentParam agentParam) {
//                        return ElementMatchers.nameStartsWith(agentParam.getInterceptClassRule());
//                    }
//
//                    @Override
//                    public ElementMatcher<MethodDescription> buildMethodsMatcher(AgentParam agentParam) {
//                        return ElementMatchers.isMethod()
//                                .and(ElementMatchers.any())
//                                .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")));
//                    }
//                }
//        };
    }

    @Override
    public Class adviceClass() {
        return TraceAdvice.class;
    }
}
