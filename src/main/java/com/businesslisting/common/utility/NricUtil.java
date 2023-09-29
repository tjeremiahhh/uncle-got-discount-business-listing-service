package com.businesslisting.common.utility;

import java.util.regex.Pattern;

public final class NricUtil {

    NricUtil() {}

    public static String mask(String plainNric) {
        boolean match = Pattern.matches("[a-zA-Z]{1}[0-9]{7}[a-zA-Z]{1}", plainNric);
        return match ? plainNric.replaceAll("([0-9]{4})", "XXXX").toUpperCase() : plainNric;
    }

}
