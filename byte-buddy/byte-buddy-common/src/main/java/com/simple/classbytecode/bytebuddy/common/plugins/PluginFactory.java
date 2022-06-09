package com.simple.classbytecode.bytebuddy.common.plugins;

import com.simple.classbytecode.bytebuddy.common.constant.AgentConstant;
import com.simple.classbytecode.bytebuddy.common.entity.AgentParam;
import com.simple.classbytecode.bytebuddy.common.plugins.impl.jvm.JvmPlugin;
import com.simple.classbytecode.bytebuddy.common.plugins.impl.trace.TracePlugin;
import com.simple.classbytecode.bytebuddy.common.util.AgentParamUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
    public static Map<String, IPlugin> PLUGIN_GROUPS = new ConcurrentHashMap<>(4);

    static {
        PLUGIN_GROUPS.put(AgentConstant.PLUGIN_TRACE, new TracePlugin());
        PLUGIN_GROUPS.put(AgentConstant.PLUGIN_JVM, new JvmPlugin());
    }

    public static List<IPlugin> listPlugins(AgentParam agentParam) {
        String plugins = agentParam.getPlugins();
        List<String> allPlugins = AgentParamUtil.dealParam(plugins);
        allPlugins.forEach(p -> {
            pluginGroup.add(PLUGIN_GROUPS.get(p));
        });
//        if (pluginGroup.size() < 1) {
//            pluginGroup.add(PLUGIN_GROUPS.Tr)
//        }

        return pluginGroup;
    }

    public static Boolean addPlugin(String pluginName, IPlugin plugin, boolean override) {
        if (override) {
            PLUGIN_GROUPS.put(pluginName, plugin);
        } else {
            PLUGIN_GROUPS.putIfAbsent(pluginName, plugin);
        }
        return true;
    }


}
