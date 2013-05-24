package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

/**
 * {@link ElementAction} implementation to submit a {@link WebElement}
 * 
 * @author sforbes
 * 
 */
public class SubmitAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Submitting element {}", element);
        element.submit();
    }

}
