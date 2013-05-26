package uk.co.stfo.adriver.assertion.driver;

import org.hamcrest.Matcher;

/**
 * Interface to assert that various page level conditions are met
 * 
 * @author sforbes
 * 
 */
public interface DriverAssertable {

    /**
     * Assert the source of the page meets the required criteria
     * 
     * @param matcher
     *            What the page source is matched against
     */
    void pageSource(Matcher<String> matcher);


    /**
     * Assert the pages url meets the required criteria
     * 
     * @param matcher
     *            What the url is matched against
     */
    void currentUrl(Matcher<String> matcher);


    /**
     * Assert the title of the page meets the required criteria
     * 
     * @param matcher
     *            What the page title is matched against
     */
    void title(Matcher<String> matcher);
}
