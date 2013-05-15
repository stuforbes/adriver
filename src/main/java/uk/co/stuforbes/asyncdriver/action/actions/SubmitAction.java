package uk.co.stuforbes.asyncdriver.action.actions;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.action.ElementAction;

public class SubmitAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);


    public void doActionOn(final WebElement element) {
        LOG.debug("Submitting element {}", element);
        element.submit();
    }

}
