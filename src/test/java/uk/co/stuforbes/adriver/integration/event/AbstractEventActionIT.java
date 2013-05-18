package uk.co.stuforbes.adriver.integration.event;

import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.openqa.selenium.By;

import uk.co.stuforbes.adriver.element.Element;
import uk.co.stuforbes.adriver.integration.AbstractDriverIT;

public abstract class AbstractEventActionIT extends AbstractDriverIT {

    @Test
    public void canLeftClickOnElements() {
        final Element eventPanel = driver.child(By.id("event-panel"));
        eventPanel.actions().click();

        eventPanel.assertThat().hasText(is("Left click event has occurred"));
    }


    @Test
    public void canRightClickOnElements() {
        final Element eventPanel = driver.child(By.id("event-panel"));
        eventPanel.actions().rightClick();

        eventPanel.assertThat().hasText(is("Right click event has occurred"));
    }


    @Test
    public void canDoubleClickOnElements() {
        final Element eventPanel = driver.child(By.id("event-panel"));
        eventPanel.actions().doubleClick();

        eventPanel.assertThat().hasText(is("Double click event has occurred"));
    }


    @Test
    public void canMoveMouseOverElements() {
        final Element eventPanel = driver.child(By.id("event-panel"));
        eventPanel.actions().moveMouseOver();

        eventPanel.assertThat().hasText(is("Mouse over event has occurred"));
    }
}
