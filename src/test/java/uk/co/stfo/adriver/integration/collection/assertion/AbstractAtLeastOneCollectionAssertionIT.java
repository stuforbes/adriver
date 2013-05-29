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

public abstract class AbstractAtLeastOneCollectionAssertionIT extends AbstractDriverIT {
    @Test
    public void atLeastOneHasAttributePassesIfAllElementsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().atLeastOneOf(3).hasAttribute("class", is("list-item"));
    }


    @Test
    public void atLeastOneHasAttributePassesIfOneElementIsValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.xpath("//li/div")).assertThat().atLeastOneOf(3).hasAttribute("class", is("item-3"));
    }


    @Test
    public void atLeastOneHasAttributeFailsIfNoItemsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().atLeastOneOf(3).hasAttribute("class", is("invalid-class"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    At least one child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Has an attribute class that is \"invalid-class\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li\n\tAn element located by tagName=li\n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void atLeastOneHasTextPassesIfAllElementsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().atLeastOneOf(3).hasText(startsWith("This is item"));
    }


    @Test
    public void atLeastOneHasTextPassesIfOneElementIsValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().atLeastOneOf(3).hasText(startsWith("This is item 1"));
    }


    @Test
    public void atLeastOneHasTextFailsIfNoItemsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().atLeastOneOf(3).hasText(is("This text is not present"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    At least one child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Has text that is \"This text is not present\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li\n\tAn element located by tagName=li\n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void atLeastOneMatchesIfAllElementsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().atLeastOneOf(3).matches(new BaseMatcher<WebElement>() {

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
    public void atLeastOneMatchesIfOneElementIsValid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().atLeastOneOf(3).matches(new BaseMatcher<WebElement>() {

            @Override
            public boolean matches(final Object item) {
                return ((WebElement) item).getText().startsWith("This is item 2");
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText("A text matcher");
            }
        });
    }


    @Test
    public void atLeastOneMatchesFailsIfNoItemsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().atLeastOneOf(3).matches(new BaseMatcher<WebElement>() {
                @Override
                public boolean matches(final Object item) {
                    return ((WebElement) item).getText().startsWith("This text is not present");
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
                    containsString("Was expecting:\n    At least one child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Matches A text matcher"));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li\n\tAn element located by tagName=li\n\tAn element located by tagName=li"));
        }
    }
}
