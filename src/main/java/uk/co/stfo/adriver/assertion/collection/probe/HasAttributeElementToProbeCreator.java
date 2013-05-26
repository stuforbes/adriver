package uk.co.stfo.adriver.assertion.collection.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.HasAttributeProbe;
import uk.co.stfo.adriver.probe.Probe;

public class HasAttributeElementToProbeCreator implements ElementToProbeCreator {

    private final String attributeName;
    private final Matcher<String> valueMatcher;


    public HasAttributeElementToProbeCreator(final String attributeName, final Matcher<String> valueMatcher) {
        this.attributeName = attributeName;
        this.valueMatcher = valueMatcher;
    }


    @Override
    public Probe createFor(final Element element) {
        return new HasAttributeProbe(element, element, attributeName, valueMatcher);
    }


    @Override
    public void describeTo(final Description description) {
        description.appendText("Has an attribute ");
        description.appendText(attributeName);
        description.appendText(" that ");
        description.appendDescriptionOf(valueMatcher);
    }
}
