package uk.co.stfo.adriver.assertion.collection.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;

import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.HasAttributeProbe;
import uk.co.stfo.adriver.probe.Probe;

public class HasAttributeElementToProbeCreatorTest extends AbstractElementToProbeCreatorTest {

    private Matcher<String> attributeMatcher;


    @SuppressWarnings("unchecked")
    @Override
    protected void createDependencies(final JUnitRuleMockery context) {
        attributeMatcher = context.mock(Matcher.class);
    }


    @Override
    protected ElementToProbeCreator initialiseProbeCreator() {
        return new HasAttributeElementToProbeCreator("an-attribute", attributeMatcher);
    }


    @Override
    protected Class<? extends Probe> requiredProbeClass() {
        return HasAttributeProbe.class;
    }


    @Override
    protected String expectedDescription() {
        return "Has an attribute an-attribute that ";
    }


    @Override
    protected void setDescriptionExpectations(final JUnitRuleMockery context, final Description description) {
        context.checking(new Expectations() {
            {
                oneOf(attributeMatcher).describeTo(description);
            }
        });
    }
}
