package uk.co.stfo.adriver.driver;

import uk.co.stfo.adriver.assertion.DriverAssertable;
import uk.co.stfo.adriver.element.ElementContainer;

public interface Driver extends ElementContainer {

    DriverAssertable assertThat();


    void navigateTo(String url);


    void close();


    void quit();
}
