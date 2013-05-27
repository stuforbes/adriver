package uk.co.stfo.adriver.integration.collection.assertion;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.integration.AbstractDriverIT;

public abstract class AbstractAllCollectionAssertionIT extends AbstractDriverIT {

    @Test
    public void allHasAttributePassesIfAllElementsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().all().hasAttribute("class", is("list-item"));
    }


    @Test
    public void allHasAttributeFailsIfItemIsNotValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.xpath("//li/div")).assertThat().all().hasAttribute("class", is("item-2"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    All children of parent Driver -> By.tagName: ul, matching criteria by xpath=//li/div, that Has an attribute class that is \"item-2\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by xpath=//li/div\n\tAn element located by xpath=//li/div"));
        }
    }


    @Test
    public void allHasTextPassesIfAllElementsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().all().hasText(startsWith("This is item"));
    }


    @Test
    public void allHasTextFailsIfItemIsNotValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().all().hasText(is("This is item 1"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    All children of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Has text that is \"This is item 1\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li\n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void allMatchesIfAllElementsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().all().matches(new BaseMatcher<WebElement>() {

            @Override
            public boolean matches(final Object item) {
                return ((WebElement) item).getText().startsWith("This is item");
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText("A text matcher");
            }
        });
    }


    @Test
    public void allMatchesFailsIfItemIsNotValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().all().matches(new BaseMatcher<WebElement>() {
                @Override
                public boolean matches(final Object item) {
                    final String text = ((WebElement) item).getText();
                    return "This is item 1".equals(text) || "This is item 2".equals(text);
                }


                @Override
                public void describeTo(final Description description) {
                    description.appendText("A text matcher");
                }
            });
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    All children of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Matches A text matcher"));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li"));
        }
    }
}
