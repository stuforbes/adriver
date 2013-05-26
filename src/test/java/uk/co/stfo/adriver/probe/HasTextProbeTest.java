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

public class HasTextProbeTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Element element;

    private Matcher<String> textMatcher;
    private SelfDescribing describer;

    private Probe probe;


    @SuppressWarnings("unchecked")
    @Before
    public void initialise() {
        this.element = context.mock(Element.class);
        this.describer = context.mock(SelfDescribing.class);
        this.textMatcher = context.mock(Matcher.class);

        this.probe = new HasTextProbe(element, describer, textMatcher);
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
        final String text = "not a match";

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));

                oneOf(webElement).getText();
                will(returnValue(text));

                oneOf(textMatcher).matches(text);
                will(returnValue(false));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void isSatisfiedIfWebElementHasBeenLocatedAndMatcherMatches() {

        final WebElement webElement = context.mock(WebElement.class);
        final String text = "a match";

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));

                oneOf(webElement).getText();
                will(returnValue(text));

                oneOf(textMatcher).matches(text);
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

                oneOf(textMatcher).describeTo(description);
            }
        });

        probe.doProbe();
        probe.describeFailureTo(description);

        assertThat(description.toString(), is("The element does not have text matching "));
    }
}
