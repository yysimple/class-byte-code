package com.simple.classbytecode.bytebuddy.trace.filter;

import com.simple.classbytecode.bytebuddy.trace.track.TrackManager;
import com.simple.rpc.common.interfaces.entity.InvokeFilterInfo;
import com.simple.rpc.common.interfaces.entity.SimpleRpcContext;
import com.simple.rpc.core.filter.InvokeBeforeFilter;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 远程调用之前的filter
 *
 * @author: WuChengXing
 * @create: 2022-06-05 18:33
 **/
public class ConsumerInvokeBeforeFilter implements InvokeBeforeFilter {

    @Override
    public SimpleRpcContext invokeBefore(SimpleRpcContext simpleRpcContext) {
        String currentSpan = TrackManager.getCurrentSpan();
        simpleRpcContext.setTraceId(currentSpan);
        return simpleRpcContext;
    }

    @Override
    public void filter(InvokeFilterInfo invokeFilterInfo) {

    }
}
