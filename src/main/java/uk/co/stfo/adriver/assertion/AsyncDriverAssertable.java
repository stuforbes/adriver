package uk.co.stfo.adriver.assertion;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.probe.MatcherProbe;

/**
 * Asynchronous implementation of {@link DriverAssertable}. All assertion
 * operations are performed through the {@link Poller}, by use of a
 * {@link MatcherProbe}.
 * 
 * @author sforbes
 * 
 */
public class AsyncDriverAssertable implements DriverAssertable {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncDriverAssertable.class);

    private final Poller poller;
    private final WebDriver webDriver;


    public AsyncDriverAssertable(final Poller poller, final WebDriver webDriver) {
        this.poller = poller;
        this.webDriver = webDriver;
    }


    @Override
    public void pageSource(final Matcher<String> matcher) {
        LOG.debug("Asserting that page source matches " + matcher.toString());
        poller.doProbe(new MatcherProbe<String>("Page Source", matcher) {
            @Override
            protected String content() {
                return webDriver.getPageSource();
            }
        });
    }


    @Override
    public void currentUrl(final Matcher<String> matcher) {
        LOG.debug("Asserting that url matches " + matcher.toString());
        poller.doProbe(new MatcherProbe<String>("Current URL", matcher) {
            @Override
            protected String content() {
                return webDriver.getCurrentUrl();
            }
        });
    }


    @Override
    public void title(final Matcher<String> matcher) {
        LOG.debug("Asserting that title matches " + matcher.toString());
        poller.doProbe(new MatcherProbe<String>("Title", matcher) {
            @Override
            protected String content() {
                return webDriver.getTitle();
            }
        });
    }
}
