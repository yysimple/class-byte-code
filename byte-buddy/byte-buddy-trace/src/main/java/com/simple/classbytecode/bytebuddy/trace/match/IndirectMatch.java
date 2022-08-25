package com.simple.classbytecode.bytebuddy.trace.match;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-25 20:57
 **/
public interface IndirectMatch extends ClassMatch {

    ElementMatcher.Junction buildJunction();

    boolean isMatch(TypeDescription typeDescription);
}
