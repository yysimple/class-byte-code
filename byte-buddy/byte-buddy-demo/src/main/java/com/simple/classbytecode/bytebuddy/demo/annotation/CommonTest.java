package com.simple.classbytecode.bytebuddy.demo.annotation;

import org.junit.Test;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 14:29
 **/
public class CommonTest {

    @Test
    public void methodInvokeTest() {
        System.out.println(methodInvoke());
    }

    public String methodInvoke() {
        CommonMethod commonMethod = new CommonMethod();
        String normal = commonMethod.normal("1111");
        return "invoke commonMethod.normal(): " + normal;
    }

    public void getCaller() {
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stack) {
            if ((ste.getClassName().indexOf("T1")) != -1) {
                System.out.println("called by " + ste.getClassName() + "." + ste.getMethodName() + "/" + ste.getFileName());
            }
        }
    }
}

class T2 {
    public void t2Method() {
        getCaller();
    }

    public static void getCaller() {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        for (int i = 0; i < stack.length; i++) {
            StackTraceElement ste = stack[i];
            System.out.println(ste.getClassName() + "." + ste.getMethodName() + "(...);");
            System.out.println(i + "--" + ste.getMethodName());
            System.out.println(i + "--" + ste.getFileName());
            System.out.println(i + "--" + ste.getLineNumber());
        }
    }
}

class T1 {
    public static void main(String[] args) {
        T2.getCaller();
    }
}



