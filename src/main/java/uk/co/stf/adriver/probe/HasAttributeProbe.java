package uk.co.stf.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stf.adriver.webdriver.WebElementLocator;

import com.google.common.base.Optional;

public class HasAttributeProbe extends AbstractElementLocatingProbe {

    private static final Logger LOG = LoggerFactory.getLogger(HasAttributeProbe.class);

    private final String attributeName;
    private final Matcher<String> valueMatcher;


    public HasAttributeProbe(final WebElementLocator elementLocator, final SelfDescribing describer,
            final String attributeName, final Matcher<String> valueMatcher) {
        super(elementLocator, describer);
        this.attributeName = attributeName;
        this.valueMatcher = valueMatcher;
    }


    @Override
    protected void describeProbeFailureTo(final Description description) {
        description.appendText("The element does not have an attribute named '");
        description.appendText(attributeName);
        description.appendText("', matching ");
        description.appendDescriptionOf(valueMatcher);
    }


    @Override
    protected boolean isElementSatisfied(final Optional<WebElement> webElement) {
        if (webElement.isPresent()) {
            final String attributeValue = webElement.get().getAttribute(attributeName);
            final boolean result = valueMatcher.matches(attributeValue);
            logResult(result);
            return result;
        }

        LOG.debug("Element does not exist");
        return false;
    }


    private void logResult(final boolean result) {
        if (LOG.isDebugEnabled()) {
            if (result) {
                LOG.debug("Element has an attribute with name {} that matches matcher {}", attributeName, valueMatcher);
            } else {
                LOG.debug("Element does not have an attribute with name {} that matches matcher {}", attributeName,
                        valueMatcher);
            }
        }
    }
}
