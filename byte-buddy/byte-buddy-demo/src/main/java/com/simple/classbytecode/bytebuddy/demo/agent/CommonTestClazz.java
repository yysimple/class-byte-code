package com.simple.classbytecode.bytebuddy.demo.agent;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: 公共的测试agent类
 *
 * @author: WuChengXing
 * @create: 2022-06-06 21:03
 **/
public class CommonTestClazz {

    public CommonTestClazz() {
        System.out.println("=== 构造器 ===");
    }

    public void testVoid() {
        System.out.println("=== 没有返回值的 ===");
    }

    public int testInt() {
        System.out.println("=== int 返回 0 ===");
        return 1;
    }

}
