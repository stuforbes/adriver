package uk.co.stfo.adriver.element;

import org.hamcrest.SelfDescribing;

import uk.co.stfo.adriver.action.ElementActions;
import uk.co.stfo.adriver.assertion.element.ElementAssertable;
import uk.co.stfo.adriver.webdriver.WebElementLocator;

/**
 * Representation of an Html element on a page. However, at the point the
 * {@link Element} is defined, it may not exist, or be available on the page.
 * 
 * @author sforbes
 * 
 */
public interface Element extends SelfDescribing, ElementContainer, WebElementLocator {

    /**
     * Make assertions on the current {@link Element}.
     * 
     * @return An {@link ElementAssertable} implementation relative to this
     */
    ElementAssertable assertThat();


    /**
     * Do something with the current {@link Element}.
     * 
     * @return An {@link ElementActions} implementation that can be used to
     *         perform actions
     */
    ElementActions perform();
}
