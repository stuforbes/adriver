package uk.co.stf.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stf.adriver.action.ElementAction;

public class ClickAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(ClickAction.class);


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Clicking on " + element);
        element.click();
    }

}
