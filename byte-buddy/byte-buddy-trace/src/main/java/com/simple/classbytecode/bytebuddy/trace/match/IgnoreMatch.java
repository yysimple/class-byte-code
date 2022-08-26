package com.simple.classbytecode.bytebuddy.trace.match;

import net.bytebuddy.matcher.ElementMatcher;

import java.beans.MethodDescriptor;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 忽略方法匹配
 *
 * @author: WuChengXing
 * @create: 2022-08-26 10:17
 **/
public interface IgnoreMatch extends MethodMatch {

    /**
     * 构建需要忽略方法的匹配规则
     *
     * @return
     */
    ElementMatcher.Junction buildIgnoreJunction();

    /**
     * 是否匹配
     *
     * @param methodDescriptor
     * @return
     */
    boolean isMatch(MethodDescriptor methodDescriptor);
}
