package uk.co.stfo.adriver.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.action.AsyncElementActions;
import uk.co.stfo.adriver.action.ElementActions;
import uk.co.stfo.adriver.action.ElementActionsFactory;
import uk.co.stfo.adriver.assertion.AsyncDriverAssertable;
import uk.co.stfo.adriver.assertion.DriverAssertable;
import uk.co.stfo.adriver.element.AsyncElement;
import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.AsyncElementCollection;
import uk.co.stfo.adriver.element.collection.AsyncListElementFactory;
import uk.co.stfo.adriver.element.collection.ElementCollection;
import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.poll.UntilTimeElapsedPoller;
import uk.co.stfo.adriver.webdriver.Traversable;

public class AsyncDriver implements Driver, Traversable, ElementActionsFactory {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncDriver.class);

    private final WebDriver webDriver;
    private final Poller poller;


    public static Driver createAsynDriver(final WebDriver webDriver, final long timeout, final long pollFrequency) {
        return new AsyncDriver(new UntilTimeElapsedPoller(timeout, pollFrequency), webDriver);
    }


    AsyncDriver(final Poller poller, final WebDriver webDriver) {
        this.poller = poller;
        this.webDriver = webDriver;
    }


    @Override
    public Element child(final By by) {
        LOG.debug("Creating child of {} with criteria {}", this, by);
        return new AsyncElement(by, poller, this, this);
    }


    @Override
    public ElementCollection children(final By by) {
        LOG.debug("Creating children collection of {} with criteria {}", this, by);
        return new AsyncElementCollection(by, poller, this, new AsyncListElementFactory(by, poller, this));
    }


    @Override
    public DriverAssertable assertThat() {
        return new AsyncDriverAssertable(poller, webDriver);
    }


    @Override
    public ElementActions createActionsFor(final Element element) {
        return new AsyncElementActions(poller, element, element, new Actions(webDriver));
    }


    @Override
    public void navigateTo(final String url) {
        webDriver.get(url);
    }


    @Override
    public void close() {
        webDriver.close();
    }


    @Override
    public void quit() {
        webDriver.quit();
    }


    @Override
    public WebElement locateWith(final By by) {
        // This will throw a NotFoundException if it doesn't exist
        return webDriver.findElement(by);
    }


    @Override
    public List<WebElement> locateAllWith(final By by) {
        return webDriver.findElements(by);
    }


    @Override
    public String toString() {
        return "Driver";
    }
}
