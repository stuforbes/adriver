package uk.co.stuforbes.adriver.element;

import org.hamcrest.SelfDescribing;

import uk.co.stuforbes.adriver.action.ElementActions;
import uk.co.stuforbes.adriver.assertion.ElementAssertable;
import uk.co.stuforbes.adriver.webdriver.WebElementLocator;

public interface Element extends SelfDescribing, ElementContainer, WebElementLocator {

    ElementAssertable assertThat();


    ElementActions actions();
}
