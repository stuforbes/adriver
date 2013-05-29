package uk.co.stfo.adriver.assertion.collection;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.poll.DoOnceOnlyPoller;
import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.webdriver.Traversable;

public class AsyncCollectionAssertableTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private By by;
    private Traversable parent;
    private Poller poller;
    private ElementFactory elementFactory;

    private WebElement webElement1;
    private WebElement webElement2;
    private WebElement webElement3;

    private Element element1;
    private Element element2;
    private Element element3;

    private CollectionAssertable assertable;


    @Before
    public void initialise() {
        this.by = By.className("a-class");
        this.parent = context.mock(Traversable.class);
        this.elementFactory = context.mock(ElementFactory.class);

        this.poller = new DoOnceOnlyPoller();

        this.webElement1 = context.mock(WebElement.class, "webElement1");
        this.webElement2 = context.mock(WebElement.class, "webElement2");
        this.webElement3 = context.mock(WebElement.class, "webElement3");

        this.element1 = context.mock(Element.class, "element1");
        this.element2 = context.mock(Element.class, "element2");
        this.element3 = context.mock(Element.class, "element3");

        this.assertable = new AsyncCollectionAssertable(by, parent, poller, elementFactory);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void allPassesIfAllElementsAreValid() {
        final Matcher<String> valueMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, false);
                prepareElement(webElement2, element2, false);
                prepareElement(webElement3, element3, false);

                prepareAttribute(webElement1, "attribute-value1", valueMatcher, true);
                prepareAttribute(webElement2, "attribute-value2", valueMatcher, true);
                prepareAttribute(webElement3, "attribute-value3", valueMatcher, true);
            }
        });

        assertable.allOf(3).hasAttribute("attribute-name", valueMatcher);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void allFailsIfAllElementsFail() {
        final Matcher<String> textMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, true);
                prepareElement(webElement2, element2, true);
                prepareElement(webElement3, element3, true);

                prepareText(webElement1, "text-value-1", textMatcher, false);
                prepareText(webElement2, "text-value-1", textMatcher, false);
                prepareText(webElement3, "text-value-1", textMatcher, false);

                oneOf(textMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertable.allOf(3).hasText(textMatcher);
            fail("Expecting an AssertionError to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("All children of parent traversable, matching criteria by className=a-class, that Has text that"));
            assertThat(ex.getMessage(), containsString("The following elements were not valid: \n\t\n\t\n\t"));
        }
    }


    @SuppressWarnings("unchecked")
    @Test
    public void allFailsIfASingleElementFails() {
        final Matcher<WebElement> webElementMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, false);
                prepareElement(webElement2, element2, false);
                prepareElement(webElement3, element3, true);

                prepareWebElement(webElement1, webElementMatcher, true);
                prepareWebElement(webElement2, webElementMatcher, true);
                prepareWebElement(webElement3, webElementMatcher, false);

                oneOf(webElementMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertable.allOf(3).matches(webElementMatcher);
            fail("Expecting an AssertionError to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("All children of parent traversable, matching criteria by className=a-class, that Matches "));
            assertThat(ex.getMessage(), containsString("The following elements were not valid: \n\t"));
        }
    }


    @SuppressWarnings("unchecked")
    @Test
    public void atLeastOnePassesIfAllElementsAreValid() {
        final Matcher<String> textMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, false);
                prepareElement(webElement2, element2, false);
                prepareElement(webElement3, element3, false);

                prepareText(webElement1, "some text 1", textMatcher, true);
                prepareText(webElement2, "some text 2", textMatcher, true);
                prepareText(webElement3, "some text 3", textMatcher, true);
            }
        });

        assertable.atLeastOneOf(3).hasText(textMatcher);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void atLeastOnePassesIfOneElementIsValid() {
        final Matcher<WebElement> webElementMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, false);
                prepareElement(webElement2, element2, false);
                prepareElement(webElement3, element3, false);

                prepareWebElement(webElement1, webElementMatcher, false);
                prepareWebElement(webElement2, webElementMatcher, false);
                prepareWebElement(webElement3, webElementMatcher, true);
            }
        });

        assertable.atLeastOneOf(3).matches(webElementMatcher);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void atLeastOneFailsIfAllElementsAreInvalid() {
        final Matcher<String> valueMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, true);
                prepareElement(webElement2, element2, true);
                prepareElement(webElement3, element3, true);

                prepareAttribute(webElement1, "attribute-value1", valueMatcher, false);
                prepareAttribute(webElement2, "attribute-value2", valueMatcher, false);
                prepareAttribute(webElement3, "attribute-value3", valueMatcher, false);

                oneOf(valueMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertable.atLeastOneOf(3).hasAttribute("attribute-name", valueMatcher);
            fail("Expecting an AssertionError to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("At least one child of parent traversable, matching criteria by className=a-class, that Has an attribute attribute-name that "));
            assertThat(ex.getMessage(), containsString("The following elements were not valid: \n\t\n\t\n\t"));
        }
    }


    @SuppressWarnings("unchecked")
    @Test
    public void nonePassesIfAllElementsAreInvalid() {
        final Matcher<WebElement> webElementMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, false);
                prepareElement(webElement2, element2, false);
                prepareElement(webElement3, element3, false);

                prepareWebElement(webElement1, webElementMatcher, false);
                prepareWebElement(webElement2, webElementMatcher, false);
                prepareWebElement(webElement3, webElementMatcher, false);
            }
        });

        assertable.noneOf(3).matches(webElementMatcher);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void noneFailsIfAllElementsAreValid() {
        final Matcher<String> valueMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, true);
                prepareElement(webElement2, element2, true);
                prepareElement(webElement3, element3, true);

                prepareAttribute(webElement1, "attribute-value-1", valueMatcher, true);
                prepareAttribute(webElement2, "attribute-value-1", valueMatcher, true);
                prepareAttribute(webElement3, "attribute-value-1", valueMatcher, true);

                oneOf(valueMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertable.noneOf(3).hasAttribute("attribute-name", valueMatcher);
            fail("Expecting an AssertionError to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("No children of parent traversable, matching criteria by className=a-class, that Has an attribute attribute-name that "));
            assertThat(ex.getMessage(), containsString("The following elements were valid: \n\t\n\t\n\t"));
        }
    }


    @SuppressWarnings("unchecked")
    @Test
    public void noneFailsIfASingleElementFails() {
        final Matcher<String> textMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                prepareParent(webElement1, webElement2, webElement3);
                prepareElementFactory(element1, element2, element3);

                prepareElement(webElement1, element1, false);
                prepareElement(webElement2, element2, false);
                prepareElement(webElement3, element3, true);

                prepareText(webElement1, "text-value1", textMatcher, true);
                prepareText(webElement2, "text-value2", textMatcher, true);
                prepareText(webElement3, "text-value3", textMatcher, false);

                oneOf(textMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertable.allOf(3).hasText(textMatcher);
            fail("Expecting an AssertionError to be thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("All children of parent traversable, matching criteria by className=a-class, that Has text that "));
            assertThat(ex.getMessage(), containsString("The following elements were not valid: \n\t"));
        }
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


    private void prepareElement(final WebElement webElement, final Element element, final boolean expectingFailure) {
        context.checking(new Expectations() {
            {
                oneOf(element).find();
                will(returnValue(webElement));

                if (expectingFailure) {
                    oneOf(element).describeTo(with(any(Description.class)));
                }
            }
        });
    }


    private void prepareAttribute(final WebElement webElement, final String attributeValue,
            final Matcher<String> valueMatcher, final boolean isMatch) {
        context.checking(new Expectations() {
            {
                oneOf(webElement).getAttribute("attribute-name");
                will(returnValue(attributeValue));

                oneOf(valueMatcher).matches(attributeValue);
                will(returnValue(isMatch));
            }
        });
    }


    private void prepareText(final WebElement webElement, final String text, final Matcher<String> textMatcher,
            final boolean isMatch) {
        context.checking(new Expectations() {
            {
                oneOf(webElement).getText();
                will(returnValue(text));

                oneOf(textMatcher).matches(text);
                will(returnValue(isMatch));
            }
        });
    }


    private void prepareWebElement(final WebElement webElement, final Matcher<WebElement> webElementMatcher,
            final boolean isMatch) {
        context.checking(new Expectations() {
            {
                oneOf(webElementMatcher).matches(webElement);
                will(returnValue(isMatch));
            }
        });
    }
}
