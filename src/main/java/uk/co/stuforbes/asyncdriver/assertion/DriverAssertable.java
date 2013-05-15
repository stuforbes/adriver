package uk.co.stuforbes.asyncdriver.assertion;

import org.hamcrest.Matcher;

public interface DriverAssertable {

    void thatPageSource(Matcher<String> matcher);


    void thatCurrentUrl(Matcher<String> matcher);

}
