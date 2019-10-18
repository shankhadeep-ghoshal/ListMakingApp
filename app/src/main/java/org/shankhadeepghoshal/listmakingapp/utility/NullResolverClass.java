package org.shankhadeepghoshal.listmakingapp.utility;

public class NullResolverClass {
    public static <E> E resolveNullValue(E argument, E zeroValue) {
        return argument == null ? zeroValue : argument;
    }
}