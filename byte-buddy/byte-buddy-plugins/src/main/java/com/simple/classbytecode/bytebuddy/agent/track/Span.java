package com.simple.classbytecode.bytebuddy.agent.track;

import java.util.Date;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:47
 **/
public class Span {

    private String traceId;

    private Date enterTime;

    public Span() {
    }

    public Span(String traceId) {
        this.traceId = traceId;
        this.enterTime = new Date();
    }

    public Span(String traceId, Date enterTime) {
        this.traceId = traceId;
        this.enterTime = enterTime;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    @Override
    public String toString() {
        return "Span{" +
                "traceId='" + traceId + '\'' +
                ", enterTime=" + enterTime +
                '}';
    }
}
