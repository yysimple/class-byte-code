package com.simple.classbytecode.bytebuddy.agent.plugins;

import com.simple.classbytecode.bytebuddy.agent.plugins.impl.jvm.JvmPlugin;
import com.simple.classbytecode.bytebuddy.agent.plugins.impl.trace.TracePlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:47
 **/
public class PluginFactory {

    public static List<IPlugin> pluginGroup = new ArrayList<>();

    static {
        //链路监控
        pluginGroup.add(new TracePlugin());
        //Jvm监控
        // pluginGroup.add(new JvmPlugin());
    }
}
