package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

/**
 * {@link ElementAction} implementation to type a single {@link Keys} to a
 * {@link WebElement}
 * 
 * @author sforbes
 * 
 */
public class TypeKeyAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);
    private final Keys key;


    public TypeKeyAction(final Keys key) {
        this.key = key;
    }


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Typing key {} on element {}", key, element);
        element.sendKeys(key);
    }
}
