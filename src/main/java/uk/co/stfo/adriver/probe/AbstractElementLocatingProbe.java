package uk.co.stfo.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

/**
 * Abstract implementation of {@link Probe} to find a {@link WebElement} using
 * the {@link ElementLocator}. If it is found, a subclass can perform a custom
 * probe action on the element. Additionally, the subclass is responsible for
 * determining whether this {@link Probe} is satisfied, using an
 * {@link Optional} {@link WebElement} as context
 * 
 * @author sforbes
 * 
 */
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


    @Override
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


    @Override
    public boolean isSatisfied() {
        final boolean result = isElementSatisfied(webElementAsOption(locatedWebElement));
        LOG.debug("Probe is " + (result ? "" : "not") + " satisfied");
        return result;
    }


    @Override
    public void describeTo(final Description description) {
        description.appendDescriptionOf(describer);
    }


    @Override
    public void describeFailureTo(final Description description) {

        if (locatedWebElement == null) {
            description.appendText("The element does not exist");
        } else {
            describeProbeFailureTo(description);
        }
    }


    /**
     * Is this probe satisfied with the resulting {@link Optional}
     * {@link WebElement}, which may not be present
     * 
     * @param webElement
     *            The {@link Optional} wrapper around a (possible)
     *            {@link WebElement}
     * @return True if the probe is satisfied, false otherwise.
     */
    protected abstract boolean isElementSatisfied(Optional<WebElement> webElement);


    /**
     * Describe to the {@link Description} why this {@link Probe} failed
     * 
     * @param description
     */
    protected abstract void describeProbeFailureTo(Description description);


    /**
     * Perform some additional probe action on the {@link WebElement}. Default
     * behaviour is no-op
     * 
     * @param webElement
     *            The {@link Optional} wrapper around a (possible)
     *            {@link WebElement}
     */
    protected void doProbeWith(final Optional<WebElement> webElement) {
        // Default behaviour is no-op
    }


    private Optional<WebElement> webElementAsOption(final WebElement webElement) {
        return Optional.fromNullable(webElement);
    }
}
