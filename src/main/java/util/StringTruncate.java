package util;

public class StringTruncate {
    public static String truncate(String str, int maxLength) {
        if (str == null) return "";
        return str.length() <= maxLength ? str : str.substring(0, maxLength - 1) + "…";
    }
}
