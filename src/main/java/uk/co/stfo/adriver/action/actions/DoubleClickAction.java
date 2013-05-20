package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

public class DoubleClickAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(DoubleClickAction.class);
    private final Actions actions;


    public DoubleClickAction(final Actions actions) {
        this.actions = actions;
    }


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Double-clicking on " + element);
        actions.doubleClick(element).perform();
    }
}
