package com.simple.test;

import com.simple.classbytecode.bytebuddy.agent.track.Span;
import org.junit.Test;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:49
 **/
public class PluginAgentMainTest {

    @Test
    public void testPlugins() {
        PluginAgentMainTest pluginAgentMainTest = new PluginAgentMainTest();
        pluginAgentMainTest.http_lt1("test");
    }

    public void http_lt1(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi1 " + name);
        http_lt2(name);
        http_lt4(name);
    }

    public void http_lt2(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi2 " + name);
        http_lt3(name);
    }

    public void http_lt3(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi3 " + name);
    }

    public void http_lt4(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi4 " + name);
        http_lt5(name);
    }

    public void http_lt5(String name) {
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试结果：hi5 " + name);
    }

    @Test
    public void testSpanCal() {
        String spanId = "0.1.1.1";

        Integer newLevel = Integer.valueOf(spanId.substring(spanId.lastIndexOf(".") + 1));
        System.out.println(newLevel);
        String newSpanId = spanId.substring(0, spanId.lastIndexOf("."));
        System.out.println(newSpanId);
    }
}
