package uk.co.stfo.adriver.element.collection.probe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.assertion.collection.result.ResultStrategy;
import uk.co.stfo.adriver.assertion.collection.result.ResultTally;
import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.element.collection.size.CollectionSizes;
import uk.co.stfo.adriver.probe.Probe;
import uk.co.stfo.adriver.webdriver.Traversable;

public class AssertOnCollectionProbeTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private By by;
    private Traversable parent;
    private ElementToProbeCreator probeCreator;
    private ElementFactory elementFactory;
    private ResultStrategy resultStrategy;

    private WebElement webElement1;
    private WebElement webElement2;
    private WebElement webElement3;

    private Element element1;
    private Element element2;
    private Element element3;

    private Probe probe;


    @Before
    public void initialise() {

        this.by = By.id("an-id");
        this.parent = context.mock(Traversable.class);
        this.probeCreator = context.mock(ElementToProbeCreator.class);
        this.elementFactory = context.mock(ElementFactory.class);

        this.webElement1 = context.mock(WebElement.class, "webElement1");
        this.webElement2 = context.mock(WebElement.class, "webElement2");
        this.webElement3 = context.mock(WebElement.class, "webElement3");

        this.element1 = context.mock(Element.class, "element1");
        this.element2 = context.mock(Element.class, "element2");
        this.element3 = context.mock(Element.class, "element3");

        this.resultStrategy = context.mock(ResultStrategy.class);

        this.probe = new AssertOnCollectionProbe(CollectionSizes.equalTo(3), by, parent, probeCreator, elementFactory,
                resultStrategy);
    }


    @Test
    public void isSatisfiedFailsIfProbingFindsNoElements() {
        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(by);
                will(returnValue(Collections.emptyList()));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void isSatisfiedFailsIfProbingFindsTooFewElements() {
        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(by);
                will(returnValue(Arrays.asList(webElement1, webElement2)));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void isSatisfiedFailsIfProbingFindsTooManyElements() {
        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(by);
                will(returnValue(Arrays.asList(webElement1, webElement2, webElement3,
                        context.mock(WebElement.class, "webElement4"))));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void isSatisfiedPassesIfOnlySuccessesAreRecorded() {

        final Probe elementProbe1 = createElementProbe(1, true);
        final Probe elementProbe2 = createElementProbe(2, true);
        final Probe elementProbe3 = createElementProbe(3, true);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                oneOf(probeCreator).createFor(element1);
                will(returnValue(elementProbe1));
                oneOf(probeCreator).createFor(element2);
                will(returnValue(elementProbe2));
                oneOf(probeCreator).createFor(element3);
                will(returnValue(elementProbe3));

                oneOf(resultStrategy).isSuccess(with(aResultOf(3, 0)));
                will(returnValue(true));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(true));
    }


    @Test
    public void isSatisfiedFailsIfOnlyFailuresAreRecorded() {
        final Probe elementProbe1 = createElementProbe(1, false);
        final Probe elementProbe2 = createElementProbe(2, false);
        final Probe elementProbe3 = createElementProbe(3, false);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                oneOf(probeCreator).createFor(element1);
                will(returnValue(elementProbe1));
                oneOf(probeCreator).createFor(element2);
                will(returnValue(elementProbe2));
                oneOf(probeCreator).createFor(element3);
                will(returnValue(elementProbe3));

                oneOf(resultStrategy).isSuccess(with(aResultOf(0, 3)));
                will(returnValue(false));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void isSatisfiedFailsIfSuccessesAndFailuresAreRecorded() {
        final Probe elementProbe1 = createElementProbe(1, true);
        final Probe elementProbe2 = createElementProbe(2, false);
        final Probe elementProbe3 = createElementProbe(3, true);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                oneOf(probeCreator).createFor(element1);
                will(returnValue(elementProbe1));
                oneOf(probeCreator).createFor(element2);
                will(returnValue(elementProbe2));
                oneOf(probeCreator).createFor(element3);
                will(returnValue(elementProbe3));

                oneOf(resultStrategy).isSuccess(with(aResultOf(2, 1)));
                will(returnValue(false));
            }
        });

        probe.doProbe();
        assertThat(probe.isSatisfied(), is(false));
    }


    @Test
    public void describeToDescribesTheProbeAccurately() {

        final StringDescription description = new StringDescription();

        context.checking(new Expectations() {
            {
                oneOf(probeCreator).describeTo(description);

                oneOf(resultStrategy).descriptionPrefix();
                will(returnValue("All children"));
            }
        });

        probe.describeTo(description);
        assertThat(description.toString(),
                is("All children of parent traversable, matching criteria by id=an-id, that "));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void describeFailureToDescribesTheFailureIncludingFailedElements() {
        final Probe elementProbe1 = createElementProbe(1, true);
        final Probe elementProbe2 = createElementProbe(2, false);
        final Probe elementProbe3 = createElementProbe(3, false);

        final StringDescription description = new StringDescription();

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                oneOf(probeCreator).createFor(element1);
                will(returnValue(elementProbe1));
                oneOf(probeCreator).createFor(element2);
                will(returnValue(elementProbe2));
                oneOf(probeCreator).createFor(element3);
                will(returnValue(elementProbe3));

                oneOf(resultStrategy).reportFailureTo(with(any(List.class)), with(any(List.class)), with(description));
            }
        });

        probe.doProbe();

        probe.describeFailureTo(description);
    }


    private void prepareParent(final WebElement... webElements) {
        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(by);
                will(returnValue(Arrays.asList(webElements)));
            }
        });
    }


    private void prepareElementFactory(final Element... elements) {
        context.checking(new Expectations() {
            {
                for (int i = 0; i < elements.length; i++) {
                    oneOf(elementFactory).createForPositionInList(i, parent);
                    will(returnValue(elements[i]));
                }
            }
        });
    }


    private Probe createElementProbe(final int number, final boolean isSatisfied) {
        final Probe probe = context.mock(Probe.class, "elementProbe" + number);
        context.checking(new Expectations() {
            {
                oneOf(probe).doProbe();
                oneOf(probe).isSatisfied();
                will(returnValue(isSatisfied));
            }
        });
        return probe;
    }


    private Matcher<ResultTally> aResultOf(final int successes, final int failures) {
        return new BaseMatcher<ResultTally>() {

            @Override
            public boolean matches(final Object item) {
                final ResultTally tally = (ResultTally) item;
                return tally.successes() == successes && tally.failures() == failures;
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText("A result tally of ");
                description.appendText(Integer.toString(successes));
                description.appendText(" successes, and ");
                description.appendText(Integer.toString(failures));
                description.appendText(" failures.");
            }
        };
    }
}
