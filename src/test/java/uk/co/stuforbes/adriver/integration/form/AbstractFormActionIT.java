package uk.co.stuforbes.adriver.integration.form;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import uk.co.stuforbes.adriver.element.Element;
import uk.co.stuforbes.adriver.integration.AbstractDriverIT;

public abstract class AbstractFormActionIT extends AbstractDriverIT {

    @Test
    public void canClickOnCheckboxes() {

        final Element top = driver.child(By.id("top"));
        top.child(By.id("content")).assertThat().hasText(isEmptyString());

        top.child(By.id("cb")).actions().click();

        top.child(By.id("content")).assertThat().hasText(is("Checkbox has been clicked"));
    }


    @Test
    public void canClickOnRadios() {
        final Element top = driver.child(By.id("top"));
        top.child(By.id("content")).assertThat().hasText(isEmptyString());

        top.child(By.id("r1")).actions().click();
        top.child(By.id("content")).assertThat().hasText(is("Radio 1 has been clicked"));
        top.child(By.id("r2")).actions().click();
        top.child(By.id("content")).assertThat().hasText(is("Radio 2 has been clicked"));
    }


    @Test
    public void canEnterTextIntoTextFields() {
        final Element top = driver.child(By.id("top"));
        top.child(By.id("content")).assertThat().hasText(isEmptyString());

        top.child(By.id("tb")).actions().type("This is some text");

        top.child(By.id("content")).assertThat().hasText(is("This is some text"));
    }


    @Test
    public void canEnterSpecificKeysIntoTextFields() {
        final Element top = driver.child(By.id("top"));
        top.child(By.id("content")).assertThat().hasText(isEmptyString());

        top.child(By.id("tb")).actions().type("This is some text");
        top.child(By.id("tb")).actions().type(Keys.BACK_SPACE);
        top.child(By.id("tb")).actions().type(Keys.BACK_SPACE);

        top.child(By.id("content")).assertThat().hasText(is("This is some te"));
    }


    @Test
    public void canClearTextFields() {
        final Element top = driver.child(By.id("top"));
        top.child(By.id("content")).assertThat().hasText(isEmptyString());

        top.child(By.id("tb")).actions().type("This is some text");
        top.child(By.id("tb")).assertThat().hasAttribute("value", is("This is some text"));

        top.child(By.id("tb")).actions().clear();
        top.child(By.id("tb")).assertThat().hasAttribute("value", is(""));
    }


    @Test
    public void canChooseOptionsInASelectBox() {
        final Element top = driver.child(By.id("top"));
        top.child(By.id("content")).assertThat().hasText(isEmptyString());

        top.child(By.id("sel")).actions().select("Option 1");
        top.child(By.id("content")).assertThat().hasText(is("Select box changed to option-1"));

        top.child(By.id("sel")).actions().select("Option 2");
        top.child(By.id("content")).assertThat().hasText(is("Select box changed to option-2"));
    }


    @Test
    public void canSubmitForms() {
        final Element top = driver.child(By.id("top"));
        top.child(By.tagName("form")).child(By.name("some-text")).actions().type("something");
        top.child(By.tagName("form")).child(By.name("some-text")).actions().submit();

        driver.asserter().thatCurrentUrl(containsString("some-text=something"));
    }
}
