package com.simple.classbytecode.bytebuddy.javaagent;

import org.junit.Test;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 22:26
 **/
public class OtherAgentTest {

    @Test
    public void testAgent(){
        AgentClazz agentClazz = new AgentClazz();
        agentClazz.normal();
        agentClazz.helloWorld();
        agentClazz.hashCode();
    }
}
