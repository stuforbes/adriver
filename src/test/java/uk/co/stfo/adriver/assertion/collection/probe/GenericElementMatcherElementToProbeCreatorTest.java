package uk.co.stfo.adriver.assertion.collection.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.element.collection.probe.ElementToProbeCreator;
import uk.co.stfo.adriver.probe.GenericElementMatcherProbe;
import uk.co.stfo.adriver.probe.Probe;

public class GenericElementMatcherElementToProbeCreatorTest extends AbstractElementToProbeCreatorTest {

    private Matcher<WebElement> webElementMatcher;


    @SuppressWarnings("unchecked")
    @Override
    protected void createDependencies(final JUnitRuleMockery context) {
        webElementMatcher = context.mock(Matcher.class);
    }


    @Override
    protected ElementToProbeCreator initialiseProbeCreator() {
        return new GenericElementMatcherElementToProbeCreator(webElementMatcher);
    }


    @Override
    protected Class<? extends Probe> requiredProbeClass() {
        return GenericElementMatcherProbe.class;
    }


    @Override
    protected String expectedDescription() {
        return "Matches ";
    }


    @Override
    protected void setDescriptionExpectations(final JUnitRuleMockery context, final Description description) {
        context.checking(new Expectations() {
            {
                oneOf(webElementMatcher).describeTo(description);
            }
        });
    }
}
