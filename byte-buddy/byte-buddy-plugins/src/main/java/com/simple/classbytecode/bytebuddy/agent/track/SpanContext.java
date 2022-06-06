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

    private static final ThreadLocal<String> trackLocal = new ThreadLocal<>();

    public static void clear(){
        trackLocal.remove();
    }

    public static String getSpanId(){
        return trackLocal.get();
    }

    public static void setSpanId(String traceId){
        trackLocal.set(traceId);
    }
}
