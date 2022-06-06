package com.simple.classbytecode.bytebuddy.trace.filter;

import com.simple.classbytecode.bytebuddy.trace.track.TrackContext;
import com.simple.rpc.common.interfaces.entity.InvokeFilterInfo;
import com.simple.rpc.common.interfaces.entity.SimpleRpcContext;
import com.simple.rpc.core.filter.RemoteInvokeBeforeFilter;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-05 14:21
 **/
public class TraceBeforeFilter implements RemoteInvokeBeforeFilter {

    @Override
    public SimpleRpcContext invokeBefore(SimpleRpcContext simpleRpcContext) {
        TrackContext.setTraceId(simpleRpcContext.getTraceId());
        return simpleRpcContext;
    }

    @Override
    public void filter(InvokeFilterInfo invokeFilterInfo) {

    }
}
