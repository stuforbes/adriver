package uk.co.stfo.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

/**
 * Implementation of {@link Probe} that asserts that a {@link WebElement} is not
 * present on the page
 * 
 * @author sforbes
 * 
 */
public class DoesNotExistProbe extends AbstractElementLocatingProbe {

    private static final Logger LOG = LoggerFactory.getLogger(DoesNotExistProbe.class);


    public DoesNotExistProbe(final WebElementLocator elementLocator, final SelfDescribing describer) {
        super(elementLocator, describer);
    }


    @Override
    public void describeFailureTo(final Description description) {
        // overridden superclass - in this instance we don't want to report an
        // error if the element doesn't exist
        // - quite the opposite
        description.appendText("The element exists");
    }


    @Override
    protected void describeProbeFailureTo(final Description description) {
        // No-op
    }


    @Override
    protected boolean isElementSatisfied(final Optional<WebElement> webElement) {
        final boolean result = !webElement.isPresent();
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
