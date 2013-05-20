package uk.co.stfo.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

public class GenericElementMatcherProbe extends AbstractElementLocatingProbe implements Probe {

    private static final Logger LOG = LoggerFactory.getLogger(GenericElementMatcherProbe.class);

    private final Matcher<WebElement> elementMatcher;


    public GenericElementMatcherProbe(final WebElementLocator elementLocator, final SelfDescribing describer,
            final Matcher<WebElement> elementMatcher) {
        super(elementLocator, describer);
        this.elementMatcher = elementMatcher;
    }


    @Override
    protected void describeProbeFailureTo(final Description description) {
        description.appendText("The element does not match ");
        description.appendDescriptionOf(elementMatcher);
    }


    @Override
    protected boolean isElementSatisfied(final Optional<WebElement> webElement) {
        if (webElement.isPresent()) {
            final boolean result = doMatch(webElement);
            logResult(result);
            return result;
        }
        LOG.debug("Element does not exist");
        return false;
    }


    private boolean doMatch(final Optional<WebElement> webElement) {
        try {
            return elementMatcher.matches(webElement.get());
        } catch (final NotFoundException ex) {
            // No-op, may be part of normal execution
        } catch (final TimeoutException ex) {
            // No-op, may be part of normal execution
        } catch (final StaleElementReferenceException ex) {
            // No-op, may be part of normal execution
        } catch (final ElementNotVisibleException ex) {
            // No-op, may be part of normal execution
        }
        return false;
    }


    private void logResult(final boolean result) {
        if (LOG.isDebugEnabled()) {
            if (result) {
                LOG.debug("Element matches matcher {}", elementMatcher);
            } else {
                LOG.debug("Element does not match matcher {}", elementMatcher);
            }
        }
    }
}
