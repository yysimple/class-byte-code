package com.simple.classbytecode.bytebuddy.trace.match;

import net.bytebuddy.matcher.ElementMatcher;

import java.beans.MethodDescriptor;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 注解匹配实现
 *
 * @author: WuChengXing
 * @create: 2022-08-26 11:05
 **/
public class AnnotationIgnoreMethodMatch implements IgnoreMatch {


    @Override
    public ElementMatcher.Junction buildIgnoreJunction() {
        return null;
    }

    @Override
    public boolean isMatch(MethodDescriptor methodDescriptor) {
        return false;
    }
}
