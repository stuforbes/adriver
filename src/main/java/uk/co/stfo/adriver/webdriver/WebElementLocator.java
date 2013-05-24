package uk.co.stfo.adriver.webdriver;

import org.openqa.selenium.WebElement;

/**
 * Interface used to locate a {@link WebElement} for the current context
 * 
 * @author sforbes
 * 
 */
public interface WebElementLocator {

    /**
     * Find the appropriate WebElement
     * 
     * @return A {@link WebElement} item.
     */
    WebElement find();
}
