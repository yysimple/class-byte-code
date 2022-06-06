package com.simple.classbytecode.bytebuddy.agent.track;

import java.util.Stack;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:47
 **/
public class TrackManager {
    private static final ThreadLocal<Stack<Span>> track = new ThreadLocal<>();

    private static Span createSpan() {
        Stack<Span> stack = track.get();
        if (stack == null) {
            stack = new Stack<>();
            track.set(stack);
        }
        String traceId;
        String spanId;
        if (stack.isEmpty()) {
            traceId = TrackContext.getTraceId();
            spanId = SpanContext.getSpanId();
            if (traceId == null) {
                traceId = "nvl";
                TrackContext.setTraceId(traceId);
                SpanContext.setSpanId("0");
            }
        } else {
            Span span = stack.peek();
            traceId = span.getTraceId();
            TrackContext.setTraceId(traceId);
        }
        return new Span(traceId);
    }

    public static Span createEntrySpan() {
        Span span = createSpan();
        Stack<Span> stack = track.get();
        stack.push(span);
        return span;
    }


    public static Span getExitSpan() {
        Stack<Span> stack = track.get();
        if (stack == null || stack.isEmpty()) {
            TrackContext.clear();
            return null;
        }
        return stack.pop();
    }

    public static Span getCurrentSpan() {
        Stack<Span> stack = track.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public static Integer calSpanLevel(String spanId) {
        String[] split = spanId.split(".");
        if (split.length < 1) {
            return 0;
        } else {

        }
    }

}
