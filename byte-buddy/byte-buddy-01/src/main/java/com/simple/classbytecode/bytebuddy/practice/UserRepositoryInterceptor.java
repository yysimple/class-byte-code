package com.simple.classbytecode.bytebuddy.practice;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Method;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-01 23:57
 **/
public class UserRepositoryInterceptor {

    public static String intercept(@Origin Method method, @AllArguments Object[] arguments) {
        return "test annotation intercept" + arguments[0];
    }
}
