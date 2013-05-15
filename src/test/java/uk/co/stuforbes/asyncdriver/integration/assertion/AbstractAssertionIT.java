package uk.co.stuforbes.asyncdriver.integration.assertion;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stuforbes.asyncdriver.element.Element;
import uk.co.stuforbes.asyncdriver.integration.AbstractDriverIT;

public abstract class AbstractAssertionIT extends AbstractDriverIT {

    @Test
    public void canAssertElementsExist() {

        final Element element = driver.child(By.tagName("div")).child(By.id("second")).child(By.tagName("p"));

        element.assertThat().doesExist();
    }


    @Test
    public void failsAssertionIfElementDoesNotExistWhenItIsExpected() {

        final Element element = driver.child(By.tagName("div")).child(By.id("second")).child(By.tagName("span"));

        try {
            element.assertThat().doesExist();
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=span, inside An element located by id=second, inside An element located by tagName=div"));
            assertThat(ex.getMessage(), containsString("but:\n    The element does not exist"));
        }
    }


    @Test
    public void canAssertDoesNotExist() {

        final Element element = driver.child(By.tagName("div")).child(By.id("fourth")).child(By.tagName("p"));

        element.assertThat().doesNotExist();
    }


    @Test
    public void failsAssertionIfElementExistsWhenItShouldNot() {

        final Element element = driver.child(By.tagName("div")).child(By.id("second")).child(By.tagName("p"));

        try {
            element.assertThat().doesNotExist();
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=p, inside An element located by id=second, inside An element located by tagName=div"));
            assertThat(ex.getMessage(), containsString("but:\n    The element exists"));
        }
    }


    @Test
    public void canAssertHasAttribute() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("p"));

        element.assertThat().hasAttribute("class", is("p-class"));
    }


    @Test
    public void failsToAssertAttributeIfElementDoesNotExist() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("img"));

        try {
            element.assertThat().hasAttribute("class", is("i-class"));
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=img, inside An element located by id=third, inside An element located by tagName=div"));
            assertThat(ex.getMessage(), containsString("but:\n    The element does not exist"));
        }
    }


    @Test
    public void failsToAssertAttributeIfElementDoesNotHaveAttribute() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("p"));

        try {
            element.assertThat().hasAttribute("style", is("display:block"));
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=p, inside An element located by id=third, inside An element located by tagName=div"));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The element does not have an attribute named 'style', matching is \"display:block\""));
        }
    }


    @Test
    public void failsToAssertAttributeIfMatcherDoesNotMatch() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("p"));

        try {
            element.assertThat().hasAttribute("class", is("i-class"));
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=p, inside An element located by id=third, inside An element located by tagName=div"));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The element does not have an attribute named 'class', matching is \"i-class\""));
        }
    }


    @Test
    public void canAssertHasText() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("p"));

        element.assertThat().hasText(endsWith("third paragraph"));
    }


    @Test
    public void failsToAssertTextIfElementDoesNotExist() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("img"));

        try {
            element.assertThat().hasText(endsWith("third paragraph"));
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=img, inside An element located by id=third, inside An element located by tagName=div"));
            assertThat(ex.getMessage(), containsString("but:\n    The element does not exist"));
        }
    }


    @Test
    public void failsToAssertTextIfMatcherDoesNotMatch() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("p"));

        try {
            element.assertThat().hasText(endsWith("second paragraph"));
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=p, inside An element located by id=third, inside An element located by tagName=div"));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The element does not have text matching a string ending with \"second paragraph\""));
        }
    }


    @Test
    public void canAssertUsingGenericWebElementMatcher() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third"));

        element.assertThat().matches(new HasChildWithTagNameMatcher("p"));
    }


    @Test
    public void failsToAssertWithElementMatcherIfElementDoesNotExist() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third")).child(By.tagName("img"));

        try {
            element.assertThat().matches(new HasChildWithTagNameMatcher("span"));
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by tagName=img, inside An element located by id=third, inside An element located by tagName=div"));
            assertThat(ex.getMessage(), containsString("but:\n    The element does not exist"));
        }
    }


    @Test
    public void failsToAssertWithElementMatcherIfMatcherDoesNotMatch() {
        final Element element = driver.child(By.tagName("div")).child(By.id("third"));

        try {
            element.assertThat().matches(new HasChildWithTagNameMatcher("span"));
            fail("Should have thrown an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    An element located by id=third, inside An element located by tagName=div"));
            assertThat(ex.getMessage(),
                    containsString("but:\n    The element does not match Element has child with tag name span"));
        }
    }

    private static final class HasChildWithTagNameMatcher extends BaseMatcher<WebElement> {

        private final String tagName;


        public HasChildWithTagNameMatcher(final String tagName) {
            this.tagName = tagName;
        }


        public boolean matches(final Object item) {
            final WebElement element = (WebElement) item;

            element.findElement(By.tagName(tagName));

            // throws a NotFoundException if not there, so if we got here it
            // must exist
            return true;
        }


        public void describeTo(final Description description) {
            description.appendText("Element has child with tag name ");
            description.appendText(tagName);
        }
    }
}
