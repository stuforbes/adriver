package uk.co.stf.adriver.element.collection.probe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.StringDescription;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.element.Element;
import uk.co.stf.adriver.element.ElementOperator;
import uk.co.stf.adriver.element.collection.ElementFactory;
import uk.co.stf.adriver.element.collection.probe.NthChildProbe;
import uk.co.stf.adriver.probe.Probe;
import uk.co.stf.adriver.webdriver.Traversable;

public class NthChildProbeTest {

    private static final int N = 1;

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private By by;
    private Traversable parent;
    private ElementFactory elementFactory;
    private ElementOperator operator;

    private Probe probe;


    @Before
    public void initialise() {
        this.by = By.id("an-id");
        this.parent = context.mock(Traversable.class, "parent-element");
        this.elementFactory = context.mock(ElementFactory.class);
        this.operator = context.mock(ElementOperator.class);

        this.probe = new NthChildProbe(by, parent, N, operator, elementFactory);
    }


    @Test
    public void probeOperatesOnNthChildOnly() {

        final WebElement webElement1 = context.mock(WebElement.class, "webElement1");
        final WebElement webElement2 = context.mock(WebElement.class, "webElement2");
        final WebElement webElement3 = context.mock(WebElement.class, "webElement3");

        final Element element2 = context.mock(Element.class, "element2");

        final List<WebElement> webElements = Arrays.asList(webElement1, webElement2, webElement3);

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(by);
                will(returnValue(webElements));

                oneOf(elementFactory).createForPositionInList(1, parent);
                will(returnValue(element2));

                oneOf(operator).doWith(element2);
            }
        });

        probe.doProbe();
    }


    @Test
    public void describeToDescribesProbe() {
        final StringDescription description = new StringDescription();
        probe.describeTo(description);

        assertThat(description.toString(), is("The 1st child of parent parent-element matching criteria by id=an-id"));
    }


    @Test
    public void describeFailureDescribesTheProbesFailure() {
        final StringDescription description = new StringDescription();
        probe.describeFailureTo(description);

        assertThat(description.toString(), is("Expected at least " + (N + 1)
                + " elements under element parent-element with criteria by id=an-id, but instead found 0"));
    }
}
