package uk.co.stf.adriver.action;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stf.adriver.action.actions.TypeTextAction;

public class MouseOverAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);

    private final Actions actions;


    public MouseOverAction(final Actions actions) {
        this.actions = actions;
    }


    public void doActionOn(final WebElement element) {
        LOG.debug("Moving mouse over element {}", element);
        actions.moveToElement(element).perform();
    }
}
