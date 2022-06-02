package com.simple.classbytecode.bytebuddy.agent.plugins;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:46
 **/
public interface IPlugin {

    //名称
    String name();

    //监控点
    InterceptPoint[] buildInterceptPoint();

    //拦截器类
    Class adviceClass();
}
