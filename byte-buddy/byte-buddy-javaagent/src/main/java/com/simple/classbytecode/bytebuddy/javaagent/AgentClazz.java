package com.simple.classbytecode.bytebuddy.javaagent;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述: agent测试类
 *
 * @author: WuChengXing
 * @create: 2022-06-02 22:27
 **/
public class AgentClazz {

    public String helloWorld() {
        return "hello world!!";
    }

    public String normal() {
        return "hello normal!!";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
