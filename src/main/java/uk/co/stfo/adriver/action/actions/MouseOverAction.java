package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

public class MouseOverAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);

    private final Actions actions;


    public MouseOverAction(final Actions actions) {
        this.actions = actions;
    }


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Moving mouse over element {}", element);
        actions.moveToElement(element).perform();
    }
}
