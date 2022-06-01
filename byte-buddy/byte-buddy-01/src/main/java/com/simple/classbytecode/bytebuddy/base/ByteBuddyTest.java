package com.simple.classbytecode.bytebuddy.base;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-01 19:05
 **/
public class ByteBuddyTest {

    public static class Hi {
        public static void main(String[] args) {
            System.out.println("helloWorld");
        }

        /**
         * 下面这个放在就不能在createClassHelloWorld这里被委托
         * @param arg
         */
        public static void main(String arg) {
            System.out.println("helloWorld");
        }
    }

    @Test
    public void testHi() throws IllegalAccessException, InstantiationException {
        String helloWorld = new ByteBuddy()
                // 父类的类类型，这里是 Object
                .subclass(Object.class)
                // 这里是指定方法,这里回去调用父类的toString，并在该类中生成
                .method(named("toString"))
                // 这里会去拦截改方法，然后反回该值
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(helloWorld);
    }

    @Test
    public void testHelloWorld() throws IllegalAccessException, InstantiationException {
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("com.simple.classbytecode.bytebuddy.HelloWorld")
                .method(named("toString")).intercept(FixedValue.value("Hello World!"))
                .make();

        // 输出类字节码
        outputClazz(dynamicType.getBytes());

        String toString = dynamicType.load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(toString);

    }

    @Test
    public void createClassHelloWorld() {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                // 创建类的名称
                .name("com.simple.classbytecode.bytebuddy.ByteBuddyHelloWorld")
                // 定义的方法名称
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
                // 该方法具备的参数
                .withParameter(String[].class, "args")
                // 拦截方法，这里是委托方法，上面对应的参数必须跟改委托方法对应，委托的类必须是public，委托之后会在生成类的main方法里面调用Hi.main
                .intercept(MethodDelegation.to(Hi.class))
                .make();

        // 输出类字节码
        outputClazz(dynamicType.getBytes());
    }

    private static void outputClazz(byte[] bytes) {
        FileOutputStream out = null;
        try {
            String pathName = ByteBuddyTest.class.getResource("/").getPath() + "ByteBuddyHelloWorld.class";
            out = new FileOutputStream(pathName);
            System.out.println("类输出路径：" + pathName);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
