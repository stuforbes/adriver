package uk.co.stfo.adriver.element;

import org.hamcrest.SelfDescribing;

import uk.co.stfo.adriver.action.ElementActions;
import uk.co.stfo.adriver.assertion.ElementAssertable;
import uk.co.stfo.adriver.webdriver.WebElementLocator;

public interface Element extends SelfDescribing, ElementContainer, WebElementLocator {

    ElementAssertable assertThat();


    ElementActions perform();
}
