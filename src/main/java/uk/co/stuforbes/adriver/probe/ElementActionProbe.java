package uk.co.stuforbes.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.adriver.action.ElementAction;
import uk.co.stuforbes.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

public class ElementActionProbe extends AbstractElementLocatingProbe {

    private static final Logger LOG = LoggerFactory.getLogger(ElementActionProbe.class);
    private final ElementAction action;


    public ElementActionProbe(final ElementAction action, final WebElementLocator elementLocator,
            final SelfDescribing describer) {
        super(elementLocator, describer);
        this.action = action;
    }


    @Override
    protected void describeProbeFailureTo(final Description description) {
        description.appendText("The element does not exist");
    }


    @Override
    protected void doProbeWith(final Optional<WebElement> webElement) {
        super.doProbeWith(webElement);

        if (webElement.isPresent()) {
            LOG.debug("Found webElement {}, about to perform action {}", webElement.get(), action);
            action.doActionOn(webElement.get());
        } else {
            LOG.debug("Web element is not present, cannot perform action yet");
        }
    }


    @Override
    protected boolean isElementSatisfied(final Optional<WebElement> webElement) {
        return webElement.isPresent();
    }

}
