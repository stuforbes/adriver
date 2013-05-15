package uk.co.stuforbes.asyncdriver.element;

import org.hamcrest.SelfDescribing;

import uk.co.stuforbes.asyncdriver.action.ElementActions;
import uk.co.stuforbes.asyncdriver.assertion.ElementAssertable;
import uk.co.stuforbes.asyncdriver.webdriver.WebElementLocator;

public interface Element extends SelfDescribing, ElementContainer, WebElementLocator {

    ElementAssertable assertThat();


    ElementActions actions();
}
