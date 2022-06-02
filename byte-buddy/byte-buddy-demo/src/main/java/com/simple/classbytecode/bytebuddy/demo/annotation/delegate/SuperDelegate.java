package com.simple.classbytecode.bytebuddy.demo.annotation.delegate;

import com.simple.classbytecode.bytebuddy.demo.annotation.CommonMethod;
import net.bytebuddy.implementation.bind.annotation.Super;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 14:17
 **/
public class SuperDelegate {

    public static String normal(String var1, @Super CommonMethod commonMethod) {
        System.out.println("invoke time:" + System.currentTimeMillis());
        return commonMethod.normal(var1);
    }
}
