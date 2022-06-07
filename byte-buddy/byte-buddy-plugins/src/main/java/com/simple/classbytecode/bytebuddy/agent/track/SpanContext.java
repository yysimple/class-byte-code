package com.simple.classbytecode.bytebuddy.agent.track;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-07 01:32
 **/
public class SpanContext {

    private static final ThreadLocal<Span> trackLocal = new ThreadLocal<>();

    public static void clear() {
        trackLocal.remove();
    }

    public static Span getSpan() {
        return trackLocal.get();
    }

    public static void setSpan(Span span) {
        trackLocal.set(span);
    }

    public static Span calEntrySpan(Span spanContext) {
        String traceId = spanContext.getTraceId();
        String spanId = spanContext.getSpanId();
        Integer level = spanContext.getLevel();
        String newSpanId = spanId + "." + (level + 1);
        return new Span(traceId, newSpanId, level);
    }

    public static Span calExitSpan(Span spanContext) {
        String traceId = spanContext.getTraceId();
        String spanId = spanContext.getSpanId();
        String[] split = spanId.split("\\.");
        if (split.length < 1) {
            return new Span(traceId, "0", 0);
        }
        Integer newLevel = Integer.valueOf(spanId.substring(spanId.lastIndexOf(".") + 1));
        String newSpanId = spanId.substring(0, spanId.lastIndexOf("."));
        return new Span(traceId, newSpanId, newLevel);
    }
}
