package com.simple.classbytecode.bytebuddy.trace.match;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 项目: class-byte-code
 * <p>
 * 功能描述:
 *
 * @author: WuChengXing
 * @create: 2022-08-25 21:00
 **/
public class PrefixMatch implements IndirectMatch {

    private String[] prefixes;

    private PrefixMatch(String... prefixes) {
        if (prefixes == null || prefixes.length == 0) {
            throw new IllegalArgumentException("prefixes argument is null or empty");
        }
        this.prefixes = prefixes;
    }

    @Override
    public ElementMatcher.Junction buildJunction() {
        ElementMatcher.Junction junction = null;

        for (String prefix : prefixes) {
            if (junction == null) {
                junction = ElementMatchers.nameStartsWith(prefix);
            } else {
                junction = junction.or(ElementMatchers.nameStartsWith(prefix));
            }
        }

        return junction;
    }

    @Override
    public boolean isMatch(TypeDescription typeDescription) {
        for (final String prefix : prefixes) {
            if (typeDescription.getName().startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static PrefixMatch nameStartsWith(final String... prefixes) {
        return new PrefixMatch(prefixes);
    }
}
