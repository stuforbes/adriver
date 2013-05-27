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

public abstract class AbstractNoneCollectionAssertionIT extends AbstractDriverIT {

    @Test
    public void noneHasAttributePassesIfAllElementsAreInvalid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().none().hasAttribute("class", is("not-present-list-item"));
    }


    @Test
    public void noneHasAttributeFailsIfOneElementIsValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.xpath("//li/div")).assertThat().none().hasAttribute("class", is("item-3"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    No child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Has an attribute class that is \"item-3\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void noneHasAttributeFailsIfAllItemsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().none().hasAttribute("class", is("list-item"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n     No child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Has an attribute class that is \"list-item\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li\n\tAn element located by tagName=li\n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void noneHasTextPassesIfAllElementsAreInvalid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().none().hasText(startsWith("This text is not present"));
    }


    @Test
    public void atLeastOneHasTextFailsIfOneElementIsValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().atLeastOne().hasText(startsWith("This is item 1"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    No child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Has text that is \"This is item 1\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void noneHasTextFailsIfAlltemsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().atLeastOne().hasText(startsWith("This is item"));
            fail("An AssertionError should have been thrown");
        } catch (final AssertionError ex) {
            assertThat(
                    ex.getMessage(),
                    containsString("Was expecting:\n    No child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Has text that starts with \"This is item\""));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li\n\tAn element located by tagName=li\n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void noneMatchesIfAllElementsAreInvalid() {
        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("li")).assertThat().none().matches(new BaseMatcher<WebElement>() {

            @Override
            public boolean matches(final Object item) {
                return ((WebElement) item).getText().startsWith("This text is not present");
            }


            @Override
            public void describeTo(final Description description) {
                description.appendText("A text matcher");
            }
        });
    }


    @Test
    public void noneFailsIfOneElementIsValid() {
        final Element list = driver.child(By.tagName("ul"));
        try {
            list.children(By.tagName("li")).assertThat().atLeastOne().matches(new BaseMatcher<WebElement>() {

                @Override
                public boolean matches(final Object item) {
                    return ((WebElement) item).getText().startsWith("This is item 2");
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
                    containsString("Was expecting:\n    No child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Matches A text matcher"));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li"));
        }
    }


    @Test
    public void noneFailsIfAllItemsAreValid() {
        final Element list = driver.child(By.tagName("ul"));

        try {
            list.children(By.tagName("li")).assertThat().atLeastOne().matches(new BaseMatcher<WebElement>() {
                @Override
                public boolean matches(final Object item) {
                    return ((WebElement) item).getText().startsWith("This is item");
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
                    containsString("Was expecting:\n    No child of parent Driver -> By.tagName: ul, matching criteria by tagName=li, that Matches A text matcher"));
            assertThat(
                    ex.getMessage(),
                    containsString("but:\n    The following elements were not valid: \n\tAn element located by tagName=li\n\tAn element located by tagName=li\n\tAn element located by tagName=li"));
        }
    }
}
