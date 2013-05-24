package uk.co.stfo.adriver.action;

import org.openqa.selenium.WebElement;

/**
 * Implementations of this interface encapsulate specific actions to be
 * performed on {@link WebElement}
 * 
 * @author sforbes
 * 
 */
public interface ElementAction {

    /**
     * Perform an action on the located {@link WebElement}
     * 
     * @param element
     *            The HTML element to be acted upon.
     */
    void doActionOn(WebElement element);
}
