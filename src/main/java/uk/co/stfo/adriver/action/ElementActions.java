package uk.co.stfo.adriver.action;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Interface to represent all actions that can be performed on an HTML element
 * 
 * 
 * @author sforbes
 * 
 */
public interface ElementActions {

    /**
     * Perform a single left-click action
     */
    void click();


    /**
     * Perform a double left-click action
     */
    void doubleClick();


    /**
     * Perform a single right-click action
     */
    void rightClick();


    /**
     * Type the characters of the text into the current element.
     * 
     * @param text
     *            The character sequence to be sent.
     */
    void type(String text);


    /**
     * Type a special character into the current element.
     * 
     * @param key
     *            The key to be sent.
     */
    void type(Keys key);


    /**
     * Clear all text in the current element. Although this can be performed on
     * any element, the only types that make sense are text inputs and
     * textareas.
     */
    void clear();


    /**
     * Sends a submit action to the current element. This assumes that the
     * current element belongs in a form, otherwise there will be no effect.
     */
    void submit();


    /**
     * Causes the mouse pointer to be moved over the current element.
     */
    void moveMouseOver();


    /**
     * Select the option in the current dropdown with the specified option text.
     * Assumes that the current element is a select box.
     * 
     * @param optionText
     *            The text of the option to be selected.
     */
    void select(String optionText);


    /**
     * Perform a custom {@link ElementAction} on the current element. The
     * {@link ElementAction} allows a {@link WebElement} to be handled, after it
     * is located
     * 
     * @param action
     *            The {@link ElementAction} to be executed, upon locating the
     *            {@link WebElement}
     */
    void perform(ElementAction action);

}
