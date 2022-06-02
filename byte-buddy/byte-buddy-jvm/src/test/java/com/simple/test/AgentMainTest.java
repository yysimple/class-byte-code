package com.simple.test;

import java.util.LinkedList;
import java.util.List;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 00:02
 **/
public class AgentMainTest {

    public static void main(String[] args) {
        while (true) {
            List<Object> list = new LinkedList<>();
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
            list.add("嗨！JavaAgent");
        }
    }
}
