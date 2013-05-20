package uk.co.stfo.adriver.action.actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.ElementAction;

public class SelectOptionAction implements ElementAction {

    private static final Logger LOG = LoggerFactory.getLogger(SelectOptionAction.class);

    private final String optionText;


    public SelectOptionAction(final String optionText) {
        super();
        this.optionText = optionText;
    }


    @Override
    public void doActionOn(final WebElement element) {
        LOG.debug("Selecting option {} in element {}", optionText, element);
        new Select(element).selectByVisibleText(optionText);
    }

}
