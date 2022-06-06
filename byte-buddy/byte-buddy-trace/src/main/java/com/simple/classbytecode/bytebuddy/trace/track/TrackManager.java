package com.simple.classbytecode.bytebuddy.trace.track;

import cn.hutool.core.util.StrUtil;

import java.util.Stack;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:30
 **/
public class TrackManager {

    private static final ThreadLocal<Stack<String>> track = new ThreadLocal<>();

    private static String createSpan() {
        // 从线程缓存中拿到此次调用栈
        Stack<String> stack = track.get();
        if (stack == null) {
            stack = new Stack<>();
            track.set(stack);
        }
        String traceId;
        if (stack.isEmpty()) {
            traceId = TrackContext.getTraceId();
            if (traceId == null) {
                traceId = "nvl";
                TrackContext.setTraceId(traceId);
            }
        } else {
            traceId = stack.peek();
            TrackContext.setTraceId(traceId);
        }
        return traceId;
    }

    public static String createEntrySpan() {
        String span = createSpan();
        Stack<String> stack = track.get();
        stack.push(span);
        return span;
    }


    public static String getExitSpan() {
        Stack<String> stack = track.get();
        if (stack == null || stack.isEmpty()) {
            TrackContext.clear();
            return null;
        }
        return stack.pop();
    }

    public static String getCurrentSpan() {
        if (!StrUtil.isBlank(TrackContext.getTraceId())) {
            return TrackContext.getTraceId();
        }
        // 获取当前线程的调用栈
        Stack<String> stack = track.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        // 不为空则将栈顶元素弹出
        return stack.peek();
    }
}
