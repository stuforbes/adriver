package uk.co.stuforbes.adriver.driver;

import uk.co.stuforbes.adriver.assertion.DriverAssertable;
import uk.co.stuforbes.adriver.element.ElementContainer;

public interface Driver extends ElementContainer {

    DriverAssertable asserter();


    void navigateTo(String url);


    void close();


    void quit();
}
