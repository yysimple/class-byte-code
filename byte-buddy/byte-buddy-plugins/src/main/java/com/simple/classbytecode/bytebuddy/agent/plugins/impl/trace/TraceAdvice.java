package com.simple.classbytecode.bytebuddy.agent.plugins.impl.trace;

import com.simple.classbytecode.bytebuddy.agent.track.Span;
import com.simple.classbytecode.bytebuddy.agent.track.TrackContext;
import com.simple.classbytecode.bytebuddy.agent.track.TrackManager;
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
        Span currentSpan = TrackManager.getCurrentSpan();
        if (null == currentSpan) {
            String traceId = UUID.randomUUID().toString();
            TrackContext.setTraceId(traceId);
        }
        TrackManager.createEntrySpan();
    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        Span exitSpan = TrackManager.getExitSpan();
        if (null == exitSpan) return;
        System.out.println("链路追踪(MQ)：" + exitSpan.getTraceId() + " " + className + "." + methodName + " 耗时：" + (System.currentTimeMillis() - exitSpan.getEnterTime().getTime()) + "ms");
    }
}
