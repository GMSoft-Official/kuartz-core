package com.kuartz.core.common.util;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public final class KzUtil {

    private KzUtil() {
        throw new UnsupportedOperationException();
    }

    public static Object[] createArray(Object... obj) {
        return obj;
    }

    public static Boolean isEmpty(Collection collection) {
        return collection == null || collection.size() <= 0;
    }

    public static Boolean isEmpty(Map map) {
        return map == null || map.size() <= 0;
    }

    public static Boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static Boolean isNull(Enum anEnum) {
        return anEnum == null;
    }

    public static Boolean isNull(Integer integer) {
        return integer == null;
    }

    public static Boolean isNull(Date date) {
        return date == null;
    }

    public static Boolean isNull(Boolean aBoolean) {
        return aBoolean == null;
    }

}
