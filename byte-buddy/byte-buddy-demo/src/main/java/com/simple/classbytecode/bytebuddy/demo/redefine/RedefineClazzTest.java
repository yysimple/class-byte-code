package com.simple.classbytecode.bytebuddy.demo.redefine;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 重定义类
 *
 * @author: WuChengXing
 * @create: 2022-06-02 10:06
 **/
public class RedefineClazzTest {

    @Test
    public void testRedefineHelloWorld() throws IOException {
        new ByteBuddy()
                // 需要重定义的类
                .redefine(HelloWorld.class)
                .make()
                .saveIn(new File(RedefineClazzTest.class.getResource("/").getPath()));
    }

    /**
     * 从其他地方拿到类资源，然后重新定义
     */
    @Test
    public void testRedefineFromOther() throws IOException {
        ClassFileLocator fileLocator = ClassFileLocator.ForJarFile.of(new File("/Users/chengxingwu/project/person-project/class-byte-code/byte-buddy/byte-buddy-01/target/byte-buddy-01-1.0.0.jar"));
        TypePool typePool = TypePool.Default.of(fileLocator);
        new ByteBuddy()
                .redefine(typePool
                        .describe("com.simple.classbytecode.bytebuddy.high.RealMethod")
                        .resolve(), fileLocator)
                .make()
                .load(ClassLoader.getSystemClassLoader());
    }

}
