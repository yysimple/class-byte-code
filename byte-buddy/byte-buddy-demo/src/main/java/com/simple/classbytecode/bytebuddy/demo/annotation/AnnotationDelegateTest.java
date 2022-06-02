package com.simple.classbytecode.bytebuddy.demo.annotation;

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
 * @create: 2022-06-02 14:21
 **/
public class AnnotationDelegateTest {

    /**
     * @throws Exception
     * @Super CommonMethod  commonMethod；
     * 这里的话可以直接拿到需要委托的类，但是调用还是走其，但是参数可以进行修改
     */
    @Test
    public void testMethodDelegation() throws Exception {
        CommonMethod commonMethod = new ByteBuddy()
                .subclass(CommonMethod.class)
                .method(ElementMatchers.named("normal"))
                .intercept(MethodDelegation.to(SuperDelegate.class))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();

        System.out.println("normal:" + commonMethod.normal("normal"));
    }


}
