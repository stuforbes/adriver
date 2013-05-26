package uk.co.stfo.adriver.assertion.element;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

/**
 * Basic set of assertions to be performed on {@link Element}s
 * 
 * @author sforbes
 * 
 */
public interface BaseElementAssertable {

    /**
     * Does the element have an attribute, with the specified name, that matches
     * the {@link Matcher}
     * 
     * @param attributeName
     *            The attribute required on the {@link Element}.
     * @param valueMatcher
     *            The {@link Matcher} implementation to be performed on the
     *            value of the attribute
     */
    void hasAttribute(String attributeName, Matcher<String> valueMatcher);


    /**
     * Does the element have inner text that matches the {@link Matcher}.
     * 
     * @param textMatcher
     *            The {@link Matcher} implementation to be performed on the
     *            inner text.
     */
    void hasText(Matcher<String> textMatcher);


    /**
     * Generic match method to assert the located {@link WebElement} itself.
     * 
     * @param elementMatcher
     *            The {@link Matcher} implementation to be performed on the
     *            {@link WebElement} itself.
     */
    void matches(Matcher<WebElement> elementMatcher);
}
