package uk.co.stfo.adriver.assertion;

import org.hamcrest.Matcher;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.probe.DoesExistProbe;
import uk.co.stfo.adriver.probe.DoesNotExistProbe;
import uk.co.stfo.adriver.probe.GenericElementMatcherProbe;
import uk.co.stfo.adriver.probe.HasAttributeProbe;
import uk.co.stfo.adriver.probe.HasTextProbe;
import uk.co.stfo.adriver.webdriver.WebElementLocator;

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


    @Override
    public void doesExist() {
        LOG.debug("Asserting that element {} does exist", elementLocator);
        poller.doProbe(new DoesExistProbe(elementLocator, elementDescriber));
    }


    @Override
    public void doesNotExist() {
        LOG.debug("Asserting that element {} does not exist", elementLocator);
        poller.doProbe(new DoesNotExistProbe(elementLocator, elementDescriber));
    }


    @Override
    public void hasAttribute(final String attributeName, final Matcher<String> valueMatcher) {
        LOG.debug("Asserting that element {} has attribute {} with matcher {}", new Object[] { elementLocator,
                attributeName, valueMatcher });
        poller.doProbe(new HasAttributeProbe(elementLocator, elementDescriber, attributeName, valueMatcher));
    }


    @Override
    public void hasText(final Matcher<String> textMatcher) {
        LOG.debug("Asserting that element {} has text {}", elementLocator, textMatcher);
        poller.doProbe(new HasTextProbe(elementLocator, elementDescriber, textMatcher));
    }


    @Override
    public void matches(final Matcher<WebElement> elementMatcher) {
        LOG.debug("Asserting that element {} matches {}", elementLocator, elementMatcher);
        poller.doProbe(new GenericElementMatcherProbe(elementLocator, elementDescriber, elementMatcher));
    }
}
