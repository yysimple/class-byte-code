package com.simple.classbytecode.bytebuddy.agent;

import com.simple.classbytecode.bytebuddy.agent.plugins.IPlugin;
import com.simple.classbytecode.bytebuddy.agent.plugins.InterceptPoint;
import com.simple.classbytecode.bytebuddy.agent.plugins.PluginFactory;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
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

        System.out.println("基于javaagent链路追踪｛源码微信公众号：bugstack虫洞栈｝");
        System.out.println("==========================================================\r\n");
        AgentBuilder agentBuilder = new AgentBuilder.Default();

        List<IPlugin> pluginGroup = PluginFactory.pluginGroup;
        for (IPlugin plugin : pluginGroup) {
            InterceptPoint[] interceptPoints = plugin.buildInterceptPoint();
            for (InterceptPoint point : interceptPoints) {

                AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
                    builder = builder.visit(Advice.to(plugin.adviceClass()).on(point.buildMethodsMatcher()));
                    return builder;
                };
                agentBuilder = agentBuilder.type(point.buildTypesMatcher()).transform(transformer).asDecorator();
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
