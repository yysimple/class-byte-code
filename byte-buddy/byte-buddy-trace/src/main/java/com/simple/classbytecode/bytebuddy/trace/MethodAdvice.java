package com.simple.classbytecode.bytebuddy.trace;

import com.simple.classbytecode.bytebuddy.trace.track.TrackContext;
import com.simple.classbytecode.bytebuddy.trace.track.TrackManager;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:29
 **/
public class MethodAdvice {

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        // 获取当前栈顶的traceId
        String traceId = TrackManager.getCurrentSpan();
        if (null == traceId) {
            // 如果不存在，则将新生成的调用id放入到线程缓存
            traceId = UUID.randomUUID().toString();
            TrackContext.setTraceId(traceId);
        }
        // 将traceId存入到缓存
        String entrySpan = TrackManager.createEntrySpan();
        System.out.println("remote trace id: " + CommonThreadLocal.traceThreadLocal.get().getTraceId());
        System.out.println("链路追踪：" + entrySpan + " " + className + "." + methodName);

    }

    @Advice.OnMethodExit()
    public static void exit(@Advice.Origin("#t") String className, @Advice.Origin("#m") String methodName) {
        TrackManager.getExitSpan();
    }
}
