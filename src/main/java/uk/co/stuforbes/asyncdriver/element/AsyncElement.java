package uk.co.stuforbes.asyncdriver.element;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.action.ElementActions;
import uk.co.stuforbes.asyncdriver.action.ElementActionsFactory;
import uk.co.stuforbes.asyncdriver.assertion.AsyncElementAssertable;
import uk.co.stuforbes.asyncdriver.assertion.ElementAssertable;
import uk.co.stuforbes.asyncdriver.poll.Poller;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

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


    public ElementCollection children(final By by, final int expectedChildrenCount) {
        LOG.debug("Creating children with criteria {}, expecting child count to be {}", by, expectedChildrenCount);
        return new AsyncElementCollection(by, expectedChildrenCount, poller, this);
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


    public void describeTo(final Description description) {
        description.appendText("An element located ");
        description.appendText(byAsString());
        if (parentLocatable instanceof SelfDescribing) {
            description.appendText(", inside ");
            description.appendDescriptionOf((SelfDescribing) parentLocatable);
        }
    }


    @Override
    public String toString() {
        return parentLocatable.toString() + " -> " + by;
    }


    private String byAsString() {
        String byString = by.toString();
        byString = byString.replaceFirst("By.", "by ");
        byString = byString.replaceFirst(": ", "=");
        return byString;
    }
}
