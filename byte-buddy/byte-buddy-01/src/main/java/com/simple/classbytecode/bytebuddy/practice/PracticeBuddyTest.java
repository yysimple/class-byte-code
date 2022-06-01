package com.simple.classbytecode.bytebuddy.practice;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.io.File;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-01 23:57
 **/
public class PracticeBuddyTest {

    @Test
    public void testByteBuddy() throws Exception {

        // 生成含有注解的泛型实现字类
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                // 创建复杂类型的泛型注解
                .subclass(TypeDescription.Generic.Builder.parameterizedType(Repository.class, String.class).build())
                // 添加类信息包括地址
                .name(Repository.class.getPackage().getName().concat(".").concat("UserRepository"))
                // 匹配处理的方法
                .method(ElementMatchers.named("queryData"))
                // 交给委托函数
                .intercept(MethodDelegation.to(UserRepositoryInterceptor.class))
                .annotateMethod(AnnotationDescription.Builder.ofType(RpcGatewayMethod.class)
                        .define("methodName", "queryData")
                        .define("methodDesc", "查询数据").build())
                .annotateType(AnnotationDescription.Builder.ofType(RpcGatewayClazz.class)
                        .define("alias", "dataApi")
                        .define("clazzDesc", "查询数据信息")
                        .define("timeOut", 350L).build())
                .make();

        // 输出类信息到目标文件夹下
        dynamicType.saveIn(new File(PracticeBuddyTest.class.getResource("/").getPath()));

        // 从目标文件夹下加载类信息
        Class<Repository<String>> repositoryClass
                = (Class<Repository<String>>) Class.forName(Repository.class.getPackage()
                .getName()
                .concat(".")
                .concat("UserRepository"));

        // 获取类注解
        RpcGatewayClazz rpcGatewayClazz = repositoryClass.getAnnotation(RpcGatewayClazz.class);
        System.out.println("RpcGatewayClazz.clazzDesc：" + rpcGatewayClazz.clazzDesc());
        System.out.println("RpcGatewayClazz.alias：" + rpcGatewayClazz.alias());
        System.out.println("RpcGatewayClazz.timeOut：" + rpcGatewayClazz.timeOut());

        // 获取方法注解
        RpcGatewayMethod rpcGatewayMethod = repositoryClass.getMethod("queryData", int.class).getAnnotation(RpcGatewayMethod.class);
        System.out.println("RpcGatewayMethod.methodName：" + rpcGatewayMethod.methodName());
        System.out.println("RpcGatewayMethod.methodDesc：" + rpcGatewayMethod.methodDesc());

        // 实例化对象
        Repository<String> repository = repositoryClass.newInstance();

        // 测试输出
        System.out.println(repository.queryData(10001));
    }

}
