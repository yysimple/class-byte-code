package com.simple.classbytecode.bytebuddy.base;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-01 19:05
 **/
public class ApiMain {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ApiMain.class.getClassLoader())
                .getLoaded();

        String str = dynamicType.newInstance().toString();
        System.out.println(str);
    }
}
