package uk.co.stfo.adriver.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Allows {@link WebElement}s to be located, by searching with a {@link By}
 * critiera
 * 
 * @author sforbes
 * 
 */
public interface Traversable {

    /**
     * Find a single {@link WebElement} on the page with the critiera
     * 
     * @param by
     *            The criteria to search by
     * @return A {@link WebElement} representation of the located item
     */
    WebElement locateWith(By by);


    /**
     * Find a list of 0 or more {@link WebElement} on the page with the critiera
     * 
     * @param by
     *            The criteria to search by
     * @return All {@link WebElement}s that could be located
     */
    List<WebElement> locateAllWith(By by);
}
