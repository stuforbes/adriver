package uk.co.stuforbes.asyncdriver.driver;

import uk.co.stuforbes.asyncdriver.assertion.DriverAssertable;
import uk.co.stuforbes.asyncdriver.element.ElementContainer;

public interface Driver extends ElementContainer {

    DriverAssertable asserter();


    void navigateTo(String url);


    void close();


    void quit();
}
