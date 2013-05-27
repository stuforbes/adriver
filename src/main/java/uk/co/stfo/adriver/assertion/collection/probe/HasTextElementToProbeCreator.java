package uk.co.stfo.adriver.assertion.collection.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.HasTextProbe;
import uk.co.stfo.adriver.probe.Probe;

/**
 * Implementation of {@link ElementToProbeCreator} that creates a
 * {@link HasTextProbe}
 * 
 * @author sforbes
 * 
 */
public class HasTextElementToProbeCreator implements ElementToProbeCreator {

    private final Matcher<String> textMatcher;


    public HasTextElementToProbeCreator(final Matcher<String> textMatcher) {
        this.textMatcher = textMatcher;
    }


    @Override
    public Probe createFor(final Element element) {
        return new HasTextProbe(element, element, textMatcher);
    }


    @Override
    public void describeTo(final Description description) {
        description.appendText("Has text that ");
        description.appendDescriptionOf(textMatcher);
    }

}
