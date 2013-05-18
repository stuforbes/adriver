package uk.co.stf.adriver.assertion;

import org.hamcrest.Matcher;

public interface DriverAssertable {

    void thatPageSource(Matcher<String> matcher);


    void thatCurrentUrl(Matcher<String> matcher);

}
