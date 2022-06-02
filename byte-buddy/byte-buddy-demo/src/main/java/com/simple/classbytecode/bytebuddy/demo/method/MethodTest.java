package com.simple.classbytecode.bytebuddy.demo.method;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 10:48
 **/
public class MethodTest {

    private String testPrivate() {
        return "";
    }

    public String testPublic() {
        return "";
    }

    public String testPublicComplex(String var1, Integer var2) {
        return var1 + var2;
    }

    public Integer testPublicComplex(Integer var1, Integer var2) {
        return var2;
    }
}
