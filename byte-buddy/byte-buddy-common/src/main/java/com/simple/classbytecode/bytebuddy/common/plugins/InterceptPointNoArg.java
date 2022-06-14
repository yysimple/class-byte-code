package com.simple.classbytecode.bytebuddy.common.plugins;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:46
 **/
public interface InterceptPointNoArg {

    /**
     * 类匹配规则
     *
     * @return
     */
    ElementMatcher<TypeDescription> buildTypesMatcher();

    /**
     * 方法匹配规则
     *
     * @return
     */
    ElementMatcher<MethodDescription> buildMethodsMatcher();
}
