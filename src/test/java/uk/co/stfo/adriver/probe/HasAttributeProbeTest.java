package uk.co.stfo.adriver.probe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.hamcrest.StringDescription;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.probe.HasAttributeProbe;
import uk.co.stfo.adriver.probe.Probe;

public class HasAttributeProbeTest {

    private static final String ATTRIBUTE_NAME = "an-attribute";

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Element element;

    private Matcher<String> valueMatcher;

    private Probe probe;

    private SelfDescribing describer;


    @SuppressWarnings("unchecked")
    @Before
    public void initialise() {
        this.element = context.mock(Element.class);
        this.valueMatcher = context.mock(Matcher.class);
        this.describer = context.mock(SelfDescribing.class);

        this.probe = new HasAttributeProbe(element, describer, ATTRIBUTE_NAME, valueMatcher);
    }


    @Test
    public void isNotSatisfiedIfWebElementNotLocated() {

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(null));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void isNotSatisfiedIfMatcherDoesNotMatch() {

        final WebElement webElement = context.mock(WebElement.class);
        final String attributeValue = "not a match";

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));

                oneOf(webElement).getAttribute(ATTRIBUTE_NAME);
                will(returnValue(attributeValue));

                oneOf(valueMatcher).matches(attributeValue);
                will(returnValue(false));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void isSatisfiedIfWebElementHasBeenLocatedAndMatcherMatches() {

        final WebElement webElement = context.mock(WebElement.class);
        final String attributeValue = "a match";

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));

                oneOf(webElement).getAttribute(ATTRIBUTE_NAME);
                will(returnValue(attributeValue));

                oneOf(valueMatcher).matches(attributeValue);
                will(returnValue(true));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(true));
    }


    @Test
    public void displaysCorrectDescriptionForProbe() {

        final StringDescription description = new StringDescription();

        context.checking(new Expectations() {
            {
                oneOf(describer).describeTo(description);
            }
        });

        probe.describeTo(description);
    }


    @Test
    public void displaysCorrectFailureDescriptionForProbeWhenElementDoesNotExist() {
        final StringDescription description = new StringDescription();

        probe.describeFailureTo(description);

        assertThat(description.toString(), is("The element does not exist"));
    }


    @Test
    public void displaysCorrectFailureDescriptionForProbe() {
        final StringDescription description = new StringDescription();

        final WebElement webElement = context.mock(WebElement.class);

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));

                oneOf(valueMatcher).describeTo(description);
            }
        });

        probe.doProbe();
        probe.describeFailureTo(description);

        assertThat(description.toString(), is("The element does not have an attribute named '" + ATTRIBUTE_NAME
                + "', matching "));
    }

}
