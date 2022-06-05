package com.simple.classbytecode.bytebuddy.trace;

import com.simple.rpc.common.interfaces.entity.SimpleRpcContext;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-05 14:22
 **/
public class CommonThreadLocal {

    public static ThreadLocal<SimpleRpcContext> traceThreadLocal = new ThreadLocal<>();
}
