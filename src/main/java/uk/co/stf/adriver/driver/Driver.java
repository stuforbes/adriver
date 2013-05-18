package uk.co.stf.adriver.driver;

import uk.co.stf.adriver.assertion.DriverAssertable;
import uk.co.stf.adriver.element.ElementContainer;

public interface Driver extends ElementContainer {

    DriverAssertable asserter();


    void navigateTo(String url);


    void close();


    void quit();
}
