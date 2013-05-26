package uk.co.stfo.adriver.assertion.collection.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.GenericElementMatcherProbe;
import uk.co.stfo.adriver.probe.Probe;

public class GenericElementMatcherElementToProbeCreator implements ElementToProbeCreator {

    private final Matcher<WebElement> elementMatcher;


    public GenericElementMatcherElementToProbeCreator(final Matcher<WebElement> elementMatcher) {
        this.elementMatcher = elementMatcher;
    }


    @Override
    public Probe createFor(final Element element) {
        return new GenericElementMatcherProbe(element, element, elementMatcher);
    }


    @Override
    public void describeTo(final Description description) {
        description.appendText("Matches ");
        description.appendDescriptionOf(elementMatcher);
    }
}
