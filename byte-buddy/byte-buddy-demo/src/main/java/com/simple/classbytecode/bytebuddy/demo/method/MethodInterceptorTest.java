package com.simple.classbytecode.bytebuddy.demo.method;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 10:46
 **/
public class MethodInterceptorTest {

    @Test
    public void testInterceptorPublic() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = new ByteBuddy().subclass(MethodTest.class)
                // 这里是拦截公共方法，但是后面的intercept是设置返回值为String所以加上对返回值是Sting类型的匹配，否则会报错
                .method(ElementMatchers.isPublic().and(ElementMatchers.returns(String.class)))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        System.out.println(aClass.getMethod("testPublic").invoke(aClass.newInstance()));
    }

    @Test
    public void testInterceptorPrivate() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = new ByteBuddy().subclass(MethodTest.class)
                // 这里是拦截公共方法，但是后面的intercept是设置返回值为String所以加上对返回值是Sting类型的匹配，否则会报错
                .method(ElementMatchers.isPrivate().and(ElementMatchers.returns(String.class)))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        // 这里获取不到private方法
        Method testPrivate = aClass.getDeclaredMethod("testPrivate");
        testPrivate.setAccessible(true);
        System.out.println(testPrivate.invoke(aClass.newInstance()));
    }

    @Test
    public void testInterceptorComplex() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = new ByteBuddy().subclass(MethodTest.class)
                // 这里是拦截公共方法、返回值为String、第一个参数是String、方法名称是testPublicComplex的一个复杂匹配
                .method(ElementMatchers.isPublic().and(ElementMatchers.returns(String.class)).and(ElementMatchers.takesArgument(1, String.class).and(ElementMatchers.named("testPublicComplex"))))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        System.out.println(aClass.getMethod("testPublicComplex", String.class, Integer.class).invoke(aClass.newInstance(), "aaa", 111));
    }
}
