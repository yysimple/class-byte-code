package com.simple.classbytecode.bytebuddy.demo.field;

import com.simple.classbytecode.bytebuddy.demo.annotation.CommonMethod;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FieldAccessor;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Modifier;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 15:10
 **/
public class FieldTest {

    @Test
    public void testField() throws Exception {
        CommonField moo = new ByteBuddy()
                .subclass(CommonField.class)
                .defineField("name", String.class)
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();
        System.out.println(moo.getClass().getDeclaredField("name") != null);
    }

    /**
     * 这里会根据NameInterceptor里面的模板在通过FieldAccessor生成对应的get、set
     *
     * @throws Exception
     */
    @Test
    public void testCreateGetSet() throws Exception {
        new ByteBuddy()
                .subclass(CommonField.class)
                .defineField("name", String.class, Modifier.PUBLIC)
                .implement(NameInterceptor.class)
                .intercept(FieldAccessor.ofBeanProperty())
                .make().saveIn(new File(FieldTest.class.getResource("/").getPath()));
    }


}
