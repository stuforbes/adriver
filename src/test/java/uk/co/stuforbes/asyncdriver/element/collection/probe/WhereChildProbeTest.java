package uk.co.stuforbes.asyncdriver.element.collection.probe;

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

import uk.co.stuforbes.asyncdriver.element.Element;
import uk.co.stuforbes.asyncdriver.element.ElementOperator;
import uk.co.stuforbes.asyncdriver.element.collection.ElementFactory;
import uk.co.stuforbes.asyncdriver.probe.Probe;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

import com.google.common.base.Predicate;

public class WhereChildProbeTest {

    private static final int EXPECTED_COUNT = 3;

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private By by;
    private Traversable parent;
    private ElementFactory elementFactory;
    private ElementOperator operator;
    private Predicate<WebElement> predicate;

    private Probe probe;


    @SuppressWarnings("unchecked")
    @Before
    public void initialise() {
        this.by = By.id("an-id");
        this.parent = context.mock(Traversable.class, "parent-element");
        this.elementFactory = context.mock(ElementFactory.class);
        this.operator = context.mock(ElementOperator.class);
        this.predicate = context.mock(Predicate.class, "a-predicate");

        this.probe = new WhereChildProbe(by, parent, EXPECTED_COUNT, predicate, operator, elementFactory);
    }


    @Test
    public void probeOperatesOnAllChildren() {

        final WebElement webElement1 = context.mock(WebElement.class, "webElement1");
        final WebElement webElement2 = context.mock(WebElement.class, "webElement2");
        final WebElement webElement3 = context.mock(WebElement.class, "webElement3");

        final Element element1 = context.mock(Element.class, "element1");
        final Element element3 = context.mock(Element.class, "element3");

        final List<WebElement> webElements = Arrays.asList(webElement1, webElement2, webElement3);

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(by);
                will(returnValue(webElements));

                oneOf(predicate).apply(webElement1);
                will(returnValue(Boolean.TRUE));

                oneOf(predicate).apply(webElement2);
                will(returnValue(Boolean.FALSE));

                oneOf(predicate).apply(webElement3);
                will(returnValue(Boolean.TRUE));

                oneOf(elementFactory).createForPositionInList(0, parent);
                will(returnValue(element1));
                oneOf(elementFactory).createForPositionInList(2, parent);
                will(returnValue(element3));

                oneOf(operator).doWith(element1);
                oneOf(operator).doWith(element3);
            }
        });

        probe.doProbe();
    }


    @Test
    public void describeToDescribesProbe() {
        final StringDescription description = new StringDescription();
        probe.describeTo(description);

        assertThat(description.toString(),
                is("All children of parent parent-element matching criteria by id=an-id and predicate a-predicate"));
    }


    @Test
    public void describeFailureDescribesTheProbesFailure() {
        final StringDescription description = new StringDescription();
        probe.describeFailureTo(description);

        assertThat(description.toString(), is("Expected " + EXPECTED_COUNT
                + " elements under element parent-element with criteria by id=an-id, but instead found 0"));
    }
}
