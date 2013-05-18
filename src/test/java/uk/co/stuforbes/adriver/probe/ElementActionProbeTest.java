package uk.co.stuforbes.adriver.probe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.SelfDescribing;
import org.hamcrest.StringDescription;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import uk.co.stuforbes.adriver.action.ElementAction;
import uk.co.stuforbes.adriver.webdriver.WebElementLocator;

public class ElementActionProbeTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private WebElementLocator elementLocator;
    private ElementAction action;
    private SelfDescribing describer;

    private Probe probe;


    @Before
    public void initialise() {

        this.action = context.mock(ElementAction.class);
        this.elementLocator = context.mock(WebElementLocator.class);
        this.describer = context.mock(SelfDescribing.class);

        this.probe = new ElementActionProbe(action, elementLocator, describer);
    }


    @Test
    public void invokesActionIfWebElementIsPresent() {

        final WebElement webElement = context.mock(WebElement.class);

        context.checking(new Expectations() {
            {
                oneOf(elementLocator).find();
                will(returnValue(webElement));

                oneOf(action).doActionOn(webElement);
            }
        });

        probe.doProbe();
    }


    @Test
    public void doesNotInvokeActionIfWebElementIsNotPresent() {
        context.checking(new Expectations() {
            {
                oneOf(elementLocator).find();
                will(returnValue(null));

            }
        });

        probe.doProbe();
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
    public void displaysCorrectFailureDescriptionForProbe() {
        final StringDescription description = new StringDescription();

        probe.describeFailureTo(description);

        assertThat(description.toString(), is("The element does not exist"));
    }
}
