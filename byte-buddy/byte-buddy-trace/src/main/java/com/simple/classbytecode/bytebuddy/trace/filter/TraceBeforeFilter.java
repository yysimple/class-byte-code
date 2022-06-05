package com.simple.classbytecode.bytebuddy.trace.filter;

import com.simple.classbytecode.bytebuddy.trace.CommonThreadLocal;
import com.simple.rpc.common.interfaces.entity.InvokeFilterInfo;
import com.simple.rpc.common.interfaces.entity.SimpleRpcContext;
import com.simple.rpc.core.filter.RemoteInvokeBeforeFilter;
import com.simple.rpc.core.network.message.Request;

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
        CommonThreadLocal.traceThreadLocal.set(simpleRpcContext);
        return simpleRpcContext;
    }

    @Override
    public void filter(InvokeFilterInfo invokeFilterInfo) {

    }
}
