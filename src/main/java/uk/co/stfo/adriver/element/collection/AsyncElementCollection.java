package uk.co.stfo.adriver.element.collection;

import org.hamcrest.StringDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.assertion.collection.AsyncCollectionAssertable;
import uk.co.stfo.adriver.assertion.collection.CollectionAssertable;
import uk.co.stfo.adriver.element.collection.probe.CountdownChildProbe;
import uk.co.stfo.adriver.element.collection.probe.EachChildProbe;
import uk.co.stfo.adriver.element.collection.probe.NthChildProbe;
import uk.co.stfo.adriver.element.collection.probe.WhereChildProbe;
import uk.co.stfo.adriver.element.collection.size.CollectionSize;
import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.probe.Probe;
import uk.co.stfo.adriver.webdriver.Traversable;

import com.google.common.base.Predicate;

/**
 * Asynchronous implementation of {@link ElementCollection}. Operations are
 * performed in {@link Probe}s, which are invoked through a {@link Poller}
 * implementation
 * 
 * @author sforbes
 * 
 */
public class AsyncElementCollection implements ElementCollection {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncElementCollection.class);

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


    @Override
    public void each(final CollectionSize collectionSize, final ElementOperator operator) {
        LOG.debug("Handling each of the expected {} children of parent {} with criteria {}", new Object[] {
                collectionSize, parent, by });
        doProbe(new EachChildProbe(by, parent, collectionSize, operator, elementFactory));
    }


    @Override
    public void countdown(final CollectionSize collectionSize, final ElementOperator operator) {
        LOG.debug("Counting down each of the expected {} children of parent {}, with criteria {}", new Object[] {
                collectionSize, parent, by });
        doProbe(new CountdownChildProbe(by, parent, collectionSize, operator, elementFactory));
    }


    @Override
    public void nth(final int n, final ElementOperator operator) {
        LOG.debug("Handling child {} of parent {} with criteria {}", new Object[] { n, parent, by });
        doProbe(new NthChildProbe(by, parent, n, operator, elementFactory));
    }


    @Override
    public void where(final CollectionSize collectionSize, final Predicate<WebElement> predicate,
            final ElementOperator operator) {
        LOG.debug("Handling {} children of parent {} with criteria {} where predicate {} is applicable", new Object[] {
                collectionSize, parent, by, predicate });
        doProbe(new WhereChildProbe(by, parent, collectionSize, predicate, operator, elementFactory));
    }


    @Override
    public CollectionAssertable assertThat() {
        return new AsyncCollectionAssertable(by, parent, poller, elementFactory);
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
