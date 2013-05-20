package uk.co.stfo.adriver.assertion;

import org.hamcrest.Matcher;

public interface DriverAssertable {

    void pageSource(Matcher<String> matcher);


    void currentUrl(Matcher<String> matcher);


    void title(Matcher<String> matcher);
}
