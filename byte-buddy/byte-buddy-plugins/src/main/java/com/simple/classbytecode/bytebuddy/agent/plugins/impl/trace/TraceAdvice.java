package com.simple.classbytecode.bytebuddy.agent.plugins.impl.trace;

import com.alibaba.fastjson.JSON;
import com.simple.classbytecode.bytebuddy.agent.track.Span;
import com.simple.classbytecode.bytebuddy.agent.track.SpanContext;
import com.simple.classbytecode.bytebuddy.agent.track.TrackContext;
import com.simple.classbytecode.bytebuddy.agent.track.TrackManager;
import com.simple.classbytecode.bytebuddy.agent.util.AgentLog;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:48
 **/
public class TraceAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        AgentLog.info("进入方法，类方法信息：{}", className + "#" + methodName);
        Span currentSpan = TrackManager.getCurrentSpan();
        if (null == currentSpan) {
            String traceId = UUID.randomUUID().toString();
            TrackContext.setTraceId(traceId);
            SpanContext.setSpanId("0");
        }
        Span entrySpan = TrackManager.createEntrySpan();
        AgentLog.info("进入方法，当前Span信息：{}", JSON.toJSONString(entrySpan));
    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        AgentLog.info("退出方法，类方法信息：{}", className + "#" + methodName);
        Span exitSpan = TrackManager.getExitSpan();
        if (null == exitSpan) {
            return;
        }
        AgentLog.info("推出方法，当前Span信息：{}", JSON.toJSONString(exitSpan));
    }
}
