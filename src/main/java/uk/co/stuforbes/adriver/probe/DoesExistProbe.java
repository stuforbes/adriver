package uk.co.stuforbes.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

public class DoesExistProbe extends AbstractElementLocatingProbe {

    private static final Logger LOG = LoggerFactory.getLogger(DoesExistProbe.class);


    public DoesExistProbe(final WebElementLocator elementLocator, final SelfDescribing describer) {
        super(elementLocator, describer);
    }


    @Override
    protected void describeProbeFailureTo(final Description description) {
        description.appendText("The element does not exist");
    }


    @Override
    protected boolean isElementSatisfied(final Optional<WebElement> webElement) {
        final boolean result = webElement.isPresent();
        logResult(result);
        return result;
    }


    private void logResult(final boolean result) {
        if (LOG.isDebugEnabled()) {
            if (result) {
                LOG.debug("Element exists");
            } else {
                LOG.debug("Element does not exist");
            }
        }
    }
}
