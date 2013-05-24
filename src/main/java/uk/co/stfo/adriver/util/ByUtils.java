package uk.co.stfo.adriver.util;

import org.openqa.selenium.By;

/**
 * Static utility methods to help with {@link By} objects
 * 
 * @author sforbes
 * 
 */
public class ByUtils {

    /**
     * Convert the {@link By} into a String, for use in errors and description
     * Strings
     * 
     * @param by
     *            The {@link By} object
     * @return Friendly String representation of the {@link By}
     */
    public static String asString(final By by) {
        String byString = by.toString();
        byString = byString.replaceFirst("By.", "by ");
        byString = byString.replaceFirst(": ", "=");
        return byString;
    }
}
