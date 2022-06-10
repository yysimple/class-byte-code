package com.simple.classbytecode.bytebuddy.common.plugins.impl.jvm;

import com.simple.classbytecode.bytebuddy.common.constant.AgentConstant;
import com.simple.classbytecode.bytebuddy.common.plugins.IPlugin;
import com.simple.classbytecode.bytebuddy.common.plugins.InterceptPoint;

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
public class JvmPlugin implements IPlugin {

    @Override
    public String name() {
        return AgentConstant.PLUGIN_JVM;
    }

    @Override
    public List<InterceptPoint> buildInterceptPoint() {
        List<InterceptPoint> interceptPoints = new ArrayList<>();
        return interceptPoints;
    }

    @Override
    public Class adviceClass() {
        return JvmAdvice.class;
    }
}
