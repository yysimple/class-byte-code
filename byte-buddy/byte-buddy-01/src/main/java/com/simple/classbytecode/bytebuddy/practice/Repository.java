package com.simple.classbytecode.bytebuddy.practice;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-01 23:55
 **/
public abstract class Repository<T> {

    public abstract T queryData(int id);
}
