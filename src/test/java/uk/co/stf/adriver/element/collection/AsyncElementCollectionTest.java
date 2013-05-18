package uk.co.stf.adriver.element.collection;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.element.Element;
import uk.co.stf.adriver.element.ElementOperator;
import uk.co.stf.adriver.element.collection.AsyncElementCollection;
import uk.co.stf.adriver.element.collection.ElementCollection;
import uk.co.stf.adriver.element.collection.ElementFactory;
import uk.co.stf.adriver.poll.Poller;
import uk.co.stf.adriver.webdriver.Traversable;
import uk.co.stf.adriver.poll.DoOnceOnlyPoller;

import com.google.common.base.Predicate;

public class AsyncElementCollectionTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Poller poller;
    private Traversable parent;
    private By criteria;

    private ElementFactory elementFactory;

    private ElementCollection elementCollection;


    @Before
    public void initialise() {
        this.criteria = By.id("children");
        this.poller = new DoOnceOnlyPoller();
        this.parent = context.mock(Traversable.class, "parent");
        this.elementFactory = context.mock(ElementFactory.class);

        this.elementCollection = new AsyncElementCollection(criteria, poller, parent, elementFactory);
    }


    @Test
    public void eachThrowsExceptionIfNotEnoughChildrenLocated() {

        final WebElement child1 = context.mock(WebElement.class, "child1");
        final WebElement child2 = context.mock(WebElement.class, "child2");
        final List<WebElement> children = Arrays.asList(child1, child2);

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(criteria);
                will(returnValue(children));
            }
        });

        try {
            elementCollection.each(3, context.mock(ElementOperator.class));
            fail("Expected an " + AssertionError.class.getName() + " to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Expected 3 elements under element parent with criteria by id=children, but instead found 2"));
        }
    }


    @Test
    public void eachThrowsExceptionIfTooManyChildrenLocated() {

        final WebElement child1 = context.mock(WebElement.class, "child1");
        final WebElement child2 = context.mock(WebElement.class, "child2");
        final WebElement child3 = context.mock(WebElement.class, "child3");
        final List<WebElement> children = Arrays.asList(child1, child2, child3);

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(criteria);
                will(returnValue(children));
            }
        });

        try {
            elementCollection.each(2, context.mock(ElementOperator.class));
            fail("Expected an " + AssertionError.class.getName() + " to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Expected 2 elements under element parent with criteria by id=children, but instead found 3"));
        }
    }


    @Test
    public void eachElementInCollectionIsOperatedOn() {

        final ElementOperator operator = context.mock(ElementOperator.class);

        final WebElement webElement1 = context.mock(WebElement.class, "webElement1");
        final WebElement webElement2 = context.mock(WebElement.class, "webElement2");
        final WebElement webElement3 = context.mock(WebElement.class, "webElement3");
        final List<WebElement> children = Arrays.asList(webElement1, webElement2, webElement3);

        final Element element1 = context.mock(Element.class, "element1");
        final Element element2 = context.mock(Element.class, "element2");
        final Element element3 = context.mock(Element.class, "element3");

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(criteria);
                will(returnValue(children));

                oneOf(elementFactory).createForPositionInList(0, parent);
                will(returnValue(element1));

                oneOf(elementFactory).createForPositionInList(1, parent);
                will(returnValue(element2));

                oneOf(elementFactory).createForPositionInList(2, parent);
                will(returnValue(element3));

                oneOf(operator).doWith(element1);
                oneOf(operator).doWith(element2);
                oneOf(operator).doWith(element3);
            }
        });

        elementCollection.each(3, operator);
    }


    @Test
    public void nthThrowsExceptionIfNotEnoughChildrenLocated() {

        final WebElement child1 = context.mock(WebElement.class, "child1");
        final WebElement child2 = context.mock(WebElement.class, "child2");
        final List<WebElement> children = Arrays.asList(child1, child2);

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(criteria);
                will(returnValue(children));
            }
        });

        try {
            elementCollection.nth(2, context.mock(ElementOperator.class));
            fail("Expected an " + AssertionError.class.getName() + " to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Expected at least 3 elements under element parent with criteria by id=children, but instead found 2"));
        }
    }


    @Test
    public void nthElementInCollectionIsOperatedOn() {

        final ElementOperator operator = context.mock(ElementOperator.class);

        final WebElement webElement1 = context.mock(WebElement.class, "webElement1");
        final WebElement webElement2 = context.mock(WebElement.class, "webElement2");
        final WebElement webElement3 = context.mock(WebElement.class, "webElement3");
        final List<WebElement> children = Arrays.asList(webElement1, webElement2, webElement3);

        final Element element3 = context.mock(Element.class, "element3");

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(criteria);
                will(returnValue(children));

                oneOf(elementFactory).createForPositionInList(2, parent);
                will(returnValue(element3));

                oneOf(operator).doWith(element3);
            }
        });

        elementCollection.nth(2, operator);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void whereThrowsExceptionIfNotEnoughChildrenLocated() {

        final WebElement child1 = context.mock(WebElement.class, "child1");
        final WebElement child2 = context.mock(WebElement.class, "child2");
        final List<WebElement> children = Arrays.asList(child1, child2);

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(criteria);
                will(returnValue(children));
            }
        });

        try {
            elementCollection.where(3, context.mock(Predicate.class), context.mock(ElementOperator.class));
            fail("Expected an " + AssertionError.class.getName() + " to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Expected 3 elements under element parent with criteria by id=children, but instead found 2"));
        }
    }


    @SuppressWarnings("unchecked")
    @Test
    public void whereOperatesOnAllApplicableElements() {

        final ElementOperator operator = context.mock(ElementOperator.class);
        final Predicate<WebElement> predicate = context.mock(Predicate.class);

        final WebElement webElement1 = context.mock(WebElement.class, "webElement1");
        final WebElement webElement2 = context.mock(WebElement.class, "webElement2");
        final WebElement webElement3 = context.mock(WebElement.class, "webElement3");
        final List<WebElement> children = Arrays.asList(webElement1, webElement2, webElement3);

        final Element element1 = context.mock(Element.class, "element1");
        final Element element3 = context.mock(Element.class, "element3");

        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(criteria);
                will(returnValue(children));

                oneOf(predicate).apply(webElement1);
                will(returnValue(true));

                oneOf(predicate).apply(webElement2);
                will(returnValue(false));

                oneOf(predicate).apply(webElement3);
                will(returnValue(true));

                oneOf(elementFactory).createForPositionInList(0, parent);
                will(returnValue(element1));
                oneOf(elementFactory).createForPositionInList(2, parent);
                will(returnValue(element3));

                oneOf(operator).doWith(element1);
                oneOf(operator).doWith(element3);
            }
        });

        elementCollection.where(3, predicate, operator);
    }
}
