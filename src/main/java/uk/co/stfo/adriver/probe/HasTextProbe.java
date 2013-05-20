package uk.co.stfo.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

public class HasTextProbe extends AbstractElementLocatingProbe {

    private static final Logger LOG = LoggerFactory.getLogger(HasTextProbe.class);

    private final Matcher<String> textMatcher;


    public HasTextProbe(final WebElementLocator elementLocator, final SelfDescribing describer,
            final Matcher<String> textMatcher) {
        super(elementLocator, describer);
        this.textMatcher = textMatcher;
    }


    @Override
    protected void describeProbeFailureTo(final Description description) {
        description.appendText("The element does not have text matching ");
        description.appendDescriptionOf(textMatcher);
    }


    @Override
    protected boolean isElementSatisfied(final Optional<WebElement> webElement) {
        if (webElement.isPresent()) {
            final boolean result = textMatcher.matches(webElement.get().getText());
            logResult(result);
            return result;
        }
        LOG.debug("Element does not exist");
        return false;
    }


    private void logResult(final boolean result) {
        if (LOG.isDebugEnabled()) {
            if (result) {
                LOG.debug("Element has a text that matches matcher {}", textMatcher);
            } else {
                LOG.debug("Element does not have text that matches matcher {}", textMatcher);
            }
        }
    }
}
