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
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.element.Element;
import uk.co.stf.adriver.probe.DoesExistProbe;
import uk.co.stf.adriver.probe.Probe;

public class DoesExistProbeTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Element element;

    private Probe probe;

    private SelfDescribing describer;


    @Before
    public void initialise() {
        this.element = context.mock(Element.class);
        this.describer = context.mock(SelfDescribing.class);

        this.probe = new DoesExistProbe(element, describer);
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
    public void isSatisfiedIfWebElementHasBeenLocated() {

        final WebElement webElement = context.mock(WebElement.class);

        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));
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
    public void displaysCorrectFailureDescriptionForProbe() {
        final StringDescription description = new StringDescription();

        probe.describeFailureTo(description);

        assertThat(description.toString(), is("The element does not exist"));
    }
}
