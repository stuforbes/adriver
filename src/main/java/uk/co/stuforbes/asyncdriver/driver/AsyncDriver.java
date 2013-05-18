package uk.co.stuforbes.asyncdriver.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.action.AsyncElementActions;
import uk.co.stuforbes.asyncdriver.action.ElementActions;
import uk.co.stuforbes.asyncdriver.action.ElementActionsFactory;
import uk.co.stuforbes.asyncdriver.assertion.AsyncDriverAssertable;
import uk.co.stuforbes.asyncdriver.assertion.DriverAssertable;
import uk.co.stuforbes.asyncdriver.element.AsyncElement;
import uk.co.stuforbes.asyncdriver.element.Element;
import uk.co.stuforbes.asyncdriver.element.collection.AsyncElementCollection;
import uk.co.stuforbes.asyncdriver.element.collection.AsyncListElementFactory;
import uk.co.stuforbes.asyncdriver.element.collection.ElementCollection;
import uk.co.stuforbes.asyncdriver.poll.Poller;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

public class AsyncDriver implements Driver, Traversable, ElementActionsFactory {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncDriver.class);

    private final WebDriver webDriver;
    private final Poller poller;


    public AsyncDriver(final Poller poller, final WebDriver webDriver) {
        this.poller = poller;
        this.webDriver = webDriver;
    }


    public Element child(final By by) {
        LOG.debug("Creating child of {} with criteria {}", this, by);
        return new AsyncElement(by, poller, this, this);
    }


    public ElementCollection children(final By by) {
        LOG.debug("Creating children collection of {} with criteria {}", this, by);
        return new AsyncElementCollection(by, poller, this, new AsyncListElementFactory(by, poller, this));
    }


    public DriverAssertable asserter() {
        return new AsyncDriverAssertable(poller, webDriver);
    }


    public ElementActions createActionsFor(final Element element) {
        return new AsyncElementActions(poller, element, element, new Actions(webDriver));
    }


    public void navigateTo(final String url) {
        webDriver.get(url);
    }


    public void close() {
        webDriver.close();
    }


    public void quit() {
        webDriver.quit();
    }


    public WebElement locateWith(final By by) {
        // This will throw a NotFoundException if it doesn't exist
        return webDriver.findElement(by);
    }


    public List<WebElement> locateAllWith(final By by) {
        return webDriver.findElements(by);
    }


    @Override
    public String toString() {
        return "Driver";
    }
}
