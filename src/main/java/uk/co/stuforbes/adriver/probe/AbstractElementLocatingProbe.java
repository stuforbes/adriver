package uk.co.stuforbes.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

public abstract class AbstractElementLocatingProbe implements Probe {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractElementLocatingProbe.class);

    private final WebElementLocator elementLocator;

    private WebElement locatedWebElement;

    private final SelfDescribing describer;


    public AbstractElementLocatingProbe(final WebElementLocator elementLocator, final SelfDescribing describer) {
        this.elementLocator = elementLocator;
        this.describer = describer;
        this.locatedWebElement = null;
    }


    public void doProbe() {

        try {

            // Don't assign immediately as locatedWebElement, as the doProbeWith
            // method may throw an exception
            // (e.g. if the element is not visible.
            final WebElement webElement = elementLocator.find();
            doProbeWith(webElementAsOption(webElement));

            // Now we can be fairly sure that webElement is valid, so assign it
            // to locatedWebElement
            locatedWebElement = webElement;

        } catch (final NotFoundException ex) {
            // No-op, this may be expected behaviour
            LOG.debug("Not Found exception when searching for element {}", elementLocator);
        } catch (final TimeoutException ex) {
            // No-op, this may be expected behaviour
            LOG.debug("Timeout exception when searching for element {}", elementLocator);
        } catch (final StaleElementReferenceException ex) {
            // No-op, this may be expected behaviour
            LOG.debug("StaleElementReferenceException when searching for element {}", elementLocator);
        } catch (final ElementNotVisibleException ex) {
            // No-op, this may be expected behaviour
            LOG.debug("ElementNotVisibleException when searching for element {}", elementLocator);
        }

    }


    public boolean isSatisfied() {
        final boolean result = isElementSatisfied(webElementAsOption(locatedWebElement));
        LOG.debug("Probe is " + (result ? "" : "not") + " satisfied");
        return result;
    }


    public void describeTo(final Description description) {
        description.appendDescriptionOf(describer);
    }


    public void describeFailureTo(final Description description) {

        if (locatedWebElement == null) {
            description.appendText("The element does not exist");
        } else {
            describeProbeFailureTo(description);
        }
    }


    protected abstract boolean isElementSatisfied(Optional<WebElement> webElement);


    protected abstract void describeProbeFailureTo(Description description);


    protected void doProbeWith(final Optional<WebElement> fromNullable) {
        // Default behaviour is no-op
    }


    private Optional<WebElement> webElementAsOption(final WebElement webElement) {
        return Optional.fromNullable(webElement);
    }
}
