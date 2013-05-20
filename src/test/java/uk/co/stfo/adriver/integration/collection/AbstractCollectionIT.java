package uk.co.stfo.adriver.integration.collection;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.ElementOperator;
import uk.co.stfo.adriver.integration.AbstractDriverIT;

import com.google.common.base.Predicate;

public abstract class AbstractCollectionIT extends AbstractDriverIT {

    @Test
    public void canClickEachCheckboxInCollection() {

        final Element content = driver.child(By.id("content"));
        content.assertThat().hasText(isEmptyString());

        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("input")).each(3, new ElementOperator() {
            @Override
            public void doWith(final Element element) {
                element.perform().click();
            }
        });

        content.assertThat().hasText(containsString("Checkbox 1 has been clicked"));
        content.assertThat().hasText(containsString("Checkbox 2 has been clicked"));
        content.assertThat().hasText(containsString("Checkbox 3 has been clicked"));
    }


    @Test
    public void canClickOnlyTheThirdCheckboxInCollection() {
        final Element content = driver.child(By.id("content"));
        content.assertThat().hasText(isEmptyString());

        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("input")).nth(2, new ElementOperator() {
            @Override
            public void doWith(final Element element) {
                element.perform().click();
            }
        });

        content.assertThat().hasText(not(containsString("Checkbox 1 has been clicked")));
        content.assertThat().hasText(not(containsString("Checkbox 2 has been clicked")));
        content.assertThat().hasText(containsString("Checkbox 3 has been clicked"));
    }


    @Test
    public void canClickOnlyTheOddNumberedCheckboxesInCollection() {
        final Element content = driver.child(By.id("content"));
        content.assertThat().hasText(isEmptyString());

        final Element list = driver.child(By.tagName("ul"));

        list.children(By.tagName("input")).where(3, new OddNumberPredicate(), new ElementOperator() {
            @Override
            public void doWith(final Element element) {
                element.perform().click();
            }
        });

        content.assertThat().hasText(containsString("Checkbox 1 has been clicked"));
        content.assertThat().hasText(not(containsString("Checkbox 2 has been clicked")));
        content.assertThat().hasText(containsString("Checkbox 3 has been clicked"));
    }

    private static final class OddNumberPredicate implements Predicate<WebElement> {

        @Override
        public boolean apply(final WebElement element) {
            final String id = element.getAttribute("id");
            if (id.length() == 3) {
                final int value = Character.digit(id.charAt(2), 10);
                return value % 2 != 0;
            }
            return false;
        }
    }
}
