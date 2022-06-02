package com.simple.classbytecode.bytebuddy.demo.annotation.add;

import java.lang.annotation.Annotation;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-06-02 15:58
 **/
public class RuntimeDefinitionImpl implements RuntimeDefinition {
    @Override
    public Class<? extends Annotation> annotationType() {
        return RuntimeDefinition.class;
    }
}
