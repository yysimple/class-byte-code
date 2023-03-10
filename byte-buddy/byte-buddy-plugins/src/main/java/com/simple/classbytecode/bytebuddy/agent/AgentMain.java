package com.simple.classbytecode.bytebuddy.agent;

import com.alibaba.fastjson.JSON;
import com.simple.classbytecode.bytebuddy.common.entity.AgentParam;
import com.simple.classbytecode.bytebuddy.common.plugins.IPlugin;
import com.simple.classbytecode.bytebuddy.common.plugins.InterceptPoint;
import com.simple.classbytecode.bytebuddy.common.plugins.PluginFactory;
import com.simple.classbytecode.bytebuddy.common.util.AgentLog;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.List;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:44
 **/
public class AgentMain {

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println(agentArgs);
        AgentLog.info("====== agent start =====");
        AgentBuilder agentBuilder = new AgentBuilder.Default();
        AgentParam agentParam = JSON.parseObject(agentArgs, AgentParam.class);
        List<IPlugin> pluginGroup = PluginFactory.listPlugins(agentParam);
        for (IPlugin plugin : pluginGroup) {
            List<InterceptPoint> interceptPoints = plugin.buildInterceptPoint();
            for (InterceptPoint point : interceptPoints) {

                AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
                    builder = builder.visit(Advice.to(plugin.adviceClass()).on(point.buildMethodsMatcher(agentParam)));
                    return builder;
                };
                agentBuilder = agentBuilder.type(point.buildTypesMatcher(agentParam)).transform(transformer).asDecorator();
            }
        }

        //监听
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

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
}
