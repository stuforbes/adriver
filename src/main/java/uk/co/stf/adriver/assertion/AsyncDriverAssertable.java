package uk.co.stf.adriver.assertion;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stf.adriver.poll.Poller;
import uk.co.stf.adriver.probe.MatcherProbe;

public class AsyncDriverAssertable implements DriverAssertable {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncDriverAssertable.class);

    private final Poller poller;
    private final WebDriver webDriver;


    public AsyncDriverAssertable(final Poller poller, final WebDriver webDriver) {
        this.poller = poller;
        this.webDriver = webDriver;
    }


    public void thatPageSource(final Matcher<String> matcher) {
        LOG.debug("Asserting that page source matches " + matcher.toString());
        poller.doProbe(new MatcherProbe<String>("Page Source", matcher) {
            @Override
            protected String content() {
                return webDriver.getPageSource();
            }
        });
    }


    public void thatCurrentUrl(final Matcher<String> matcher) {
        LOG.debug("Asserting that url matches " + matcher.toString());
        poller.doProbe(new MatcherProbe<String>("Current URL", matcher) {
            @Override
            protected String content() {
                return webDriver.getCurrentUrl();
            }
        });
    }

}
