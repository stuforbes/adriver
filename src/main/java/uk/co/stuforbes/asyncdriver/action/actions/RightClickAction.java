package uk.co.stuforbes.asyncdriver.action.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.action.ElementAction;

public class RightClickAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(RightClickAction.class);

    private final Actions actions;


    public RightClickAction(final Actions actions) {
        this.actions = actions;
    }


    public void doActionOn(final WebElement element) {
        LOG.debug("Right clicking on element {}", element);
        actions.contextClick(element).perform();
    }

}
