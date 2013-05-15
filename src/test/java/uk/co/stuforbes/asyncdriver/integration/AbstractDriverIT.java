package uk.co.stuforbes.asyncdriver.integration;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.driver.AsyncDriver;
import uk.co.stuforbes.asyncdriver.poll.UntilTimeElapsedPoller;

public abstract class AbstractDriverIT {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDriverIT.class);

    protected AsyncDriver driver;


    @Before
    public void initialise() {
        this.driver = new AsyncDriver(new UntilTimeElapsedPoller(timeout(), pollFreq()), new FirefoxDriver());

        final URL resource = getClass().getResource(String.format("/html/%s/%s", folder(), filename()));

        LOG.debug("Navigating to " + resource.toString());
        driver.navigateTo(resource.toString());
    }


    @After
    public void tearDown() {
        driver.close();
    }


    protected abstract String filename();


    protected abstract String folder();


    protected abstract long timeout();


    protected abstract long pollFreq();
}
