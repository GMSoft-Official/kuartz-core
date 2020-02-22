package com.kuartz.core.auth.util;

public class KuartzSecurityUtil {

    public static final String DEFAULT_PRIVILEGE_SEPERATOR = "_";

    public static String[] parsePrivilege(String privilege) {
        return privilege.split(DEFAULT_PRIVILEGE_SEPERATOR);
    }
}
