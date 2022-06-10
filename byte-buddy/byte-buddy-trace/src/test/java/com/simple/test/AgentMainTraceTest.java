package com.simple.test;

import com.simple.test.hello.HelloWorld;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:38
 **/
public class AgentMainTraceTest {

    public static void main(String[] args) {
        AgentMainTraceTest test = new AgentMainTraceTest();
        test.http_lt1();
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.helloWorld();

        //线程二
//        new Thread(() -> {
//            new AgentMainTraceTest().http_lt1();
//        }).start();
    }


    public void http_lt1() {
        System.out.println("测试结果：hi1");
        http_lt2();
    }

    public void http_lt2() {
        System.out.println("测试结果：hi2");
        http_lt3();
        hashCode();
        toString();
    }

    public void http_lt3() {
        System.out.println("测试结果：hi3");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "AgentMainTraceTest{}";
    }

    public String helloWorld(){
        return "111";
    }
}
