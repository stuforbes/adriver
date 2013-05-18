package uk.co.stf.adriver.probe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.SelfDescribing;
import org.hamcrest.StringDescription;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.element.Element;
import uk.co.stf.adriver.probe.DoesNotExistProbe;
import uk.co.stf.adriver.probe.Probe;

public class DoesNotExistProbeTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Element element;
    private SelfDescribing describer;

    private Probe probe;


    @Before
    public void initialise() {
        this.element = context.mock(Element.class);
        this.describer = context.mock(SelfDescribing.class);

        this.probe = new DoesNotExistProbe(element, describer);
    }


    @Test
    public void isSatisfiedIfWebElementNotLocated() {

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(null));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(true));
    }


    @Test
    public void isSatisfiedIfTimeoutExceptionIsThrown() {

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(throwException(new TimeoutException()));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(true));
    }


    @Test
    public void isSatisfiedIfStaleElementExceptionIsThrown() {

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(throwException(new StaleElementReferenceException("Exception")));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(true));
    }


    @Test
    public void isNotSatisfiedIfWebElementHasBeenLocated() {

        final WebElement webElement = context.mock(WebElement.class);

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
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

        assertThat(description.toString(), is("The element exists"));
    }
}
