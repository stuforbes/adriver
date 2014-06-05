package uk.co.stfo.adriver.driver;

import org.openqa.selenium.WebDriver;

/**
 * Implementations of this interface encapsulate specific actions to be
 * performed on {@link org.openqa.selenium.WebDriver}
 *
 * @author sforbes
 *
 */
public interface DriverAction {

    /**
     * Perform an action on the located {@link org.openqa.selenium.WebDriver}
     *
     * @param webDriver
     *            The single @{@link WebDriver} instance
     */
    void doActionOn(WebDriver webDriver);
}
