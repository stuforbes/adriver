package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

public class TypeTextAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(TypeTextAction.class);
    private final String text;


    public TypeTextAction(final String text) {
        this.text = text;
    }


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Typing text {} on element {}", text, element);
        element.sendKeys(text);
    }
}
