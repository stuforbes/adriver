package uk.co.stf.adriver.element;

import org.hamcrest.SelfDescribing;

import uk.co.stf.adriver.action.ElementActions;
import uk.co.stf.adriver.assertion.ElementAssertable;
import uk.co.stf.adriver.webdriver.WebElementLocator;

public interface Element extends SelfDescribing, ElementContainer, WebElementLocator {

    ElementAssertable assertThat();


    ElementActions actions();
}
