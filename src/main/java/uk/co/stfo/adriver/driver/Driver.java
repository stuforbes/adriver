package uk.co.stfo.adriver.driver;

import org.openqa.selenium.WebDriver;

import uk.co.stfo.adriver.assertion.driver.DriverAssertable;
import uk.co.stfo.adriver.driver.output.DriverOutput;
import uk.co.stfo.adriver.element.ElementContainer;

/**
 * The main representation of a page. From here, we can:
 * <p/>
 * <ul>
 * <li>Navigate to other pages</li>
 * <li>Make assertions at a browser level (page source, page title etc)</li>
 * <li>Find elements on the page</li>
 * </ul>
 * 
 * @author sforbes
 * 
 */
public interface Driver extends ElementContainer {

    /**
     * Make assertions on the driver itself
     * 
     * @return The {@link DriverAssertable} that will make the actual
     *         assertions.
     */
    DriverAssertable assertThat();


    /**
     * Move the driver to a different url. In the {@link AsyncDriver}
     * implementation, this performs a get operation on the underlying
     * {@link WebDriver}.
     * 
     * @param url
     *            The url to navigate to
     */
    void navigateTo(String url);


    /**
     * Clears the session data
     */
    void closeSession();


    /**
     * Close the current window. In the {@link AsyncDriver} implementation, this
     * performs a close operation on the underlying {@link WebDriver}
     */
    void close();


    /**
     * Quit the browser. In the {@link AsyncDriver} implementation, this
     * performs a quit operation on the underlying {@link WebDriver}
     */
    void quit();

    /**
     * Perform a custom action on the {@link org.openqa.selenium.WebDriver}
     * @param action The custom action to be performed
     */
    void perform(DriverAction action);

    /**
     * Output various details of the Driver. Useful for debugging errors or
     * failed tests.
     * 
     * @return {@link DriverOutput} that can output various types of data.
     */
    DriverOutput dump();

}
