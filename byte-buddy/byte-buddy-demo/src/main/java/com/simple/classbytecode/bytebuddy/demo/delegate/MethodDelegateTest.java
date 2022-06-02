package com.simple.classbytecode.bytebuddy.demo.delegate;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 11:23
 **/
public class MethodDelegateTest {

    @Test
    public void testMethodDelegation() throws Exception {
        NormalClazz clazz = new ByteBuddy()
                .subclass(NormalClazz.class)
                .method(ElementMatchers.named("oneParam").or(ElementMatchers.named("twoParam")))
                // 被委托的实现必须是一个static的方法；NormalClazz中的方法在DelegateClazz中必须全部有实现，方法名可以不一样，参数列表必须一致
                .intercept(MethodDelegation.to(DelegateClazz.class))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();

        System.out.println("oneParam:" +  clazz.oneParam("one"));
        System.out.println("twoParam:" + clazz.twoParam("two", "two"));
    }

}
