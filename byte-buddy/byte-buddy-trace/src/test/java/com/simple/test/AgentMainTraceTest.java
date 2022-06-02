package com.simple.test;

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

        //线程一
        new Thread(() -> new AgentMainTraceTest().http_lt1()).start();

        //线程二
        new Thread(() -> {
            new AgentMainTraceTest().http_lt1();
        }).start();
    }


    public void http_lt1() {
        System.out.println("测试结果：hi1");
        http_lt2();
    }

    public void http_lt2() {
        System.out.println("测试结果：hi2");
        http_lt3();
    }

    public void http_lt3() {
        System.out.println("测试结果：hi3");
    }
}
