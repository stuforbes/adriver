package uk.co.stuforbes.asyncdriver.action.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.action.ElementAction;

public class TypeKeyAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);
    private final Keys key;


    public TypeKeyAction(final Keys key) {
        this.key = key;
    }


    public void doActionOn(final WebElement element) {
        LOG.debug("Typing key {} on element {}", key, element);
        element.sendKeys(key);
    }
}
