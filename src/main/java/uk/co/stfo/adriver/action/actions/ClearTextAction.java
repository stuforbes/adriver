package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

public class ClearTextAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Clearing on element {}", element);
        element.clear();
    }

}