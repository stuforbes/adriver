package uk.co.stf.adriver.util;

import org.openqa.selenium.By;

public class ByUtils {

    public static String asString(final By by) {
        String byString = by.toString();
        byString = byString.replaceFirst("By.", "by ");
        byString = byString.replaceFirst(": ", "=");
        return byString;
    }
}
