package com.simple.classbytecode.bytebuddy.trace.track;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:30
 **/
public class TrackContext {

    private static final ThreadLocal<String> trackLocal = new ThreadLocal<String>();

    public static void clear() {
        trackLocal.remove();
    }

    public static String getTraceId() {
        return trackLocal.get();
    }

    public static void setTraceId(String traceId) {
        trackLocal.set(traceId);
    }
}
