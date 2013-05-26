package uk.co.stfo.adriver.assertion.collection.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;

import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.HasTextProbe;
import uk.co.stfo.adriver.probe.Probe;

public class HasTextElementToProbeCreatorTest extends AbstractElementToProbeCreatorTest {

    private Matcher<String> textMatcher;


    @SuppressWarnings("unchecked")
    @Override
    protected void createDependencies(final JUnitRuleMockery context) {
        textMatcher = context.mock(Matcher.class);
    }


    @Override
    protected ElementToProbeCreator initialiseProbeCreator() {
        return new HasTextElementToProbeCreator(textMatcher);
    }


    @Override
    protected Class<? extends Probe> requiredProbeClass() {
        return HasTextProbe.class;
    }


    @Override
    protected String expectedDescription() {
        return "Has text that ";
    }


    @Override
    protected void setDescriptionExpectations(final JUnitRuleMockery context, final Description description) {
        context.checking(new Expectations() {
            {
                oneOf(textMatcher).describeTo(description);
            }
        });
    }
}
