package com.simple.classbytecode.bytebuddy.demo.delegate;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 11:29
 **/
public class NormalClazz {

    public String oneParam(String var1) {
        return "param1: " + var1;
    }

    public String twoParam(String var1, String var2) {
        return "param1: " + var1 + "--" + "param2: " +var2;
    }

}
