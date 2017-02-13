package cheanxin.util;

import cheanxin.constant.UtilConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 273cn on 16/12/19.
 */
public class StringUtil {
    public static final char UNDERLINE='_';

    /**
     * Parse camel to under line.
     * If first character is Upper Case, do not insert under line.
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i<len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Whether elements of stringArray1 separated by STRING_ARRAY_SEPARATOR contains all elements of stringArray2.
     * @param String stringArray1
     * @param String stringArray2
     * @return
     */
    public static boolean containsAll(String string1, String string2) {
        String[] strings = string1.trim().split(UtilConstants.STRING_ARRAY_SEPARATOR);
        Set<String> stringSet = new HashSet<>(strings.length);
        for (String string : strings) {
            stringSet.add(string);
        }
        for (String string : string2.trim().split(UtilConstants.STRING_ARRAY_SEPARATOR)) {
            if (!stringSet.contains(string))
                return false;
        }
        return true;
    }
}
