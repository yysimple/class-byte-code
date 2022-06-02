package com.simple.classbytecode.bytebuddy.demo.annotation;

import com.simple.classbytecode.bytebuddy.demo.annotation.add.RuntimeDefinitionImpl;
import com.simple.classbytecode.bytebuddy.demo.annotation.delegate.SuperCallDelegate;
import com.simple.classbytecode.bytebuddy.demo.annotation.delegate.SuperDelegate;
import com.simple.classbytecode.bytebuddy.demo.field.CommonField;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Modifier;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: {@link com.simple.classbytecode.bytebuddy.high.MethodMonitor}；该类中有其他许多注解的使用方式
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
    public void testSuperDelegation() throws Exception {
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

    /**
     * @throws Exception
     * @Super CommonMethod  commonMethod；
     */
    @Test
    public void testSuperCallDelegation() throws Exception {
        CommonMethod commonMethod = new ByteBuddy()
                .subclass(CommonMethod.class)
                .method(ElementMatchers.named("normal"))
                .intercept(MethodDelegation.to(SuperCallDelegate.class))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();

        System.out.println(commonMethod.normal("normal"));
    }

    /**
     * 这里会为委托类的方法+字段指定注解
     *
     * @throws Exception
     */
    @Test
    public void testAnnotation() throws Exception {
        new ByteBuddy()
                .subclass(CommonMethod.class)
                .defineField("name", String.class, Modifier.PUBLIC)
                .annotateField(new RuntimeDefinitionImpl())
                .method(ElementMatchers.named("normal"))
                .intercept(MethodDelegation.to(SuperDelegate.class))
                .annotateMethod(new RuntimeDefinitionImpl())
                .make()
                // @RuntimeDefinition
                // public String name;
                .saveIn(new File(AnnotationDelegateTest.class.getResource("/").getPath()));
    }


}
