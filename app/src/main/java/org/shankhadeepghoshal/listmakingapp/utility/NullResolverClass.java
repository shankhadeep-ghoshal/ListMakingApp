package org.shankhadeepghoshal.listmakingapp.utility;

import java.util.Collection;

public class NullResolverClass {
    public static <E> E resolveNullValue(E argument, E zeroValue) {
        return argument == null ? zeroValue : argument;
    }

    public static <E extends Collection> boolean checkNullOrEmptyCollection(E collectionObject) {
        return collectionObject != null && collectionObject.size() > 0;
    }
}