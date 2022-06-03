package com.simple.test;

import org.junit.Test;

import java.util.Stack;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-03 13:13
 **/
public class StackTest {

    Stack<String> stack = new Stack<>();

    @Test
    public void peek(){
        stack.push("1");
        stack.push("2");
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack);
        stack.push("3");
        System.out.println(stack);

    }
}
