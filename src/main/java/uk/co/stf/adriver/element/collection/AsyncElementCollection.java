package uk.co.stf.adriver.element.collection;

import org.hamcrest.StringDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.element.ElementOperator;
import uk.co.stf.adriver.element.collection.probe.EachChildProbe;
import uk.co.stf.adriver.element.collection.probe.NthChildProbe;
import uk.co.stf.adriver.element.collection.probe.WhereChildProbe;
import uk.co.stf.adriver.poll.Poller;
import uk.co.stf.adriver.probe.Probe;
import uk.co.stf.adriver.webdriver.Traversable;

import com.google.common.base.Predicate;

public class AsyncElementCollection implements ElementCollection {

    private final By by;
    private final Poller poller;
    private final Traversable parent;
    private final ElementFactory elementFactory;


    public AsyncElementCollection(final By by, final Poller poller, final Traversable parent,
            final ElementFactory elementFactory) {
        this.by = by;
        this.poller = poller;
        this.parent = parent;
        this.elementFactory = elementFactory;
    }


    public void each(final int expectedCount, final ElementOperator operator) {
        doProbe(new EachChildProbe(by, parent, expectedCount, operator, elementFactory));
    }


    public void nth(final int n, final ElementOperator operator) {
        doProbe(new NthChildProbe(by, parent, n, operator, elementFactory));
    }


    public void where(final int expectedCount, final Predicate<WebElement> predicate, final ElementOperator operator) {
        doProbe(new WhereChildProbe(by, parent, expectedCount, predicate, operator, elementFactory));
    }


    private void doProbe(final Probe probe) {
        poller.doProbe(probe);
        if (!probe.isSatisfied()) {
            final StringDescription description = new StringDescription();
            probe.describeFailureTo(description);
            throw new AssertionError(description.toString());
        }
    }
}
