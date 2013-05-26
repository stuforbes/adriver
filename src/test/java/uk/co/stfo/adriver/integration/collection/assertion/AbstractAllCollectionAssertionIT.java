package uk.co.stfo.adriver.integration.collection.assertion;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.openqa.selenium.By;

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
}
