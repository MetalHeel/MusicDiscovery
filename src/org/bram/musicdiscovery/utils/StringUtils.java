package org.bram.musicdiscovery.utils;

public class StringUtils {
    public static boolean equals(String left, String right) {
        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        return left.equals(right);
    }
    public static boolean isBlank(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isNotBlank(String string) {
        return string != null && string.length() > 0;
    }

    public static boolean isNumeric(String string) {
        if (isBlank(string)) {
            return false;
        }
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
