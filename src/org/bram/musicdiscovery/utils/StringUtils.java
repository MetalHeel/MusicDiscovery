package org.bram.musicdiscovery.utils;

public class StringUtils {
    public static boolean isBlank(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isNotBlank(String string) {
        return string != null && string.length() > 0;
    }
}
