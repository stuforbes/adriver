package uk.co.stf.adriver.element;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stf.adriver.action.ElementActions;
import uk.co.stf.adriver.action.ElementActionsFactory;
import uk.co.stf.adriver.assertion.AsyncElementAssertable;
import uk.co.stf.adriver.assertion.ElementAssertable;
import uk.co.stf.adriver.element.collection.AsyncElementCollection;
import uk.co.stf.adriver.element.collection.AsyncListElementFactory;
import uk.co.stf.adriver.element.collection.ElementCollection;
import uk.co.stf.adriver.poll.Poller;
import uk.co.stf.adriver.util.ByUtils;
import uk.co.stf.adriver.webdriver.Traversable;

public class AsyncElement implements Element, Traversable {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncElement.class);

    private final By by;
    private final Poller poller;
    private final Traversable parentLocatable;

    private final ElementActionsFactory elementActionsFactory;


    public AsyncElement(final By by, final Poller poller, final Traversable parentLocatable,
            final ElementActionsFactory elementActionsFactory) {
        this.by = by;
        this.poller = poller;
        this.parentLocatable = parentLocatable;
        this.elementActionsFactory = elementActionsFactory;
    }


    public WebElement find() {
        LOG.debug("Finding element from parent {} using criteria {}", parentLocatable, by);
        return parentLocatable.locateWith(this.by);
    }


    public Element child(final By by) {
        LOG.debug("Creating child with criteria {}", by);
        return new AsyncElement(by, poller, this, elementActionsFactory);
    }


    public ElementCollection children(final By by) {
        LOG.debug("Creating children with criteria {}", by);
        return new AsyncElementCollection(by, poller, this, new AsyncListElementFactory(by, poller,
                elementActionsFactory));
    }


    public ElementAssertable assertThat() {
        return new AsyncElementAssertable(poller, this, this);
    }


    public ElementActions actions() {
        return elementActionsFactory.createActionsFor(this);
    }


    public WebElement locateWith(final By by) {
        // This will throw a NotFoundException if it doesn't exist
        LOG.debug("Locating child with criteria {}", by);
        return find().findElement(by);
    }


    public List<WebElement> locateAllWith(final By by) {
        LOG.debug("Locating all children with criteria {}", by);
        return find().findElements(by);
    }


    public void describeTo(final Description description) {
        description.appendText("An element located ");
        description.appendText(ByUtils.asString(by));
        if (parentLocatable instanceof SelfDescribing) {
            description.appendText(", inside ");
            description.appendDescriptionOf((SelfDescribing) parentLocatable);
        }
    }


    @Override
    public String toString() {
        return parentLocatable.toString() + " -> " + by;
    }
}
