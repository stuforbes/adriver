package uk.co.stfo.adriver.integration;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.driver.AsyncDriver;
import uk.co.stfo.adriver.driver.Driver;

public abstract class AbstractDriverIT {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDriverIT.class);

    protected Driver driver;


    @Before
    public void initialise() {
        this.driver = AsyncDriver.createAsynDriver(createWebDriver(), timeout(), pollFreq());

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


    private WebDriver createWebDriver() {
        return new FirefoxDriver();
    }
}
