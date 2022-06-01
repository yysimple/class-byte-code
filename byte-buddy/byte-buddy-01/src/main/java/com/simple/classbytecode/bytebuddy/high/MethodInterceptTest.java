package com.simple.classbytecode.bytebuddy.high;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-01 23:33
 **/
public class MethodInterceptTest {

    @Test
    public void testByteBuddy() throws Exception {

        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(RealMethod.class)
                // 这里可以使用any(),表示匹配所有
                .method(ElementMatchers.named("testByteBuddy"))
                // 委托的代理方法
                .intercept(MethodDelegation.to(MethodMonitor.class))
                .make();

        // 加载类
        Class<?> clazz = dynamicType.load(MethodInterceptTest.class.getClassLoader())
                .getLoaded();

        // 反射调用
        clazz.getMethod("testByteBuddy", String.class, Integer.class).invoke(clazz.newInstance(), "10001", 1111);

    }
}
