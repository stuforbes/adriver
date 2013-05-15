package uk.co.stuforbes.asyncdriver.assertion;

import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.poll.Poller;
import uk.co.stuforbes.asyncdriver.probe.DoesExistProbe;
import uk.co.stuforbes.asyncdriver.probe.DoesNotExistProbe;
import uk.co.stuforbes.asyncdriver.probe.GenericElementMatcherProbe;
import uk.co.stuforbes.asyncdriver.probe.HasAttributeProbe;
import uk.co.stuforbes.asyncdriver.probe.HasTextProbe;
import uk.co.stuforbes.asyncdriver.webdriver.WebElementLocator;

public class AsyncElementAssertable implements ElementAssertable {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncElementAssertable.class);

    private final Poller poller;

    private final WebElementLocator elementLocator;

    private final SelfDescribing elementDescriber;


    public AsyncElementAssertable(final Poller poller, final WebElementLocator elementLocator,
            final SelfDescribing elementDescriber) {
        this.poller = poller;
        this.elementLocator = elementLocator;
        this.elementDescriber = elementDescriber;
    }


    public void doesExist() {
        LOG.debug("Asserting that element {} does exist", elementLocator);
        poller.doProbe(new DoesExistProbe(elementLocator, elementDescriber));
    }


    public void doesNotExist() {
        LOG.debug("Asserting that element {} does not exist", elementLocator);
        poller.doProbe(new DoesNotExistProbe(elementLocator, elementDescriber));
    }


    public void hasAttribute(final String attributeName, final Matcher<String> valueMatcher) {
        LOG.debug("Asserting that element {} has attribute {} with matcher {}", new Object[] { elementLocator,
                attributeName, valueMatcher });
        poller.doProbe(new HasAttributeProbe(elementLocator, elementDescriber, attributeName, valueMatcher));
    }


    public void hasText(final Matcher<String> textMatcher) {
        LOG.debug("Asserting that element {} has text {}", elementLocator, textMatcher);
        poller.doProbe(new HasTextProbe(elementLocator, elementDescriber, textMatcher));
    }


    public void matches(final Matcher<WebElement> elementMatcher) {
        LOG.debug("Asserting that element {} matches {}", elementLocator, elementMatcher);
        poller.doProbe(new GenericElementMatcherProbe(elementLocator, elementDescriber, elementMatcher));
    }
}
