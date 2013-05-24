package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

/**
 * {@link ElementAction} implementation to left-click on a {@link WebElement}
 * 
 * @author sforbes
 * 
 */
public class ClickAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(ClickAction.class);


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Clicking on " + element);
        element.click();
    }

}
