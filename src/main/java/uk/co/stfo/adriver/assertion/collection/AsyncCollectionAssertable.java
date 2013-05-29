package uk.co.stfo.adriver.assertion.collection;

import org.openqa.selenium.By;

import uk.co.stfo.adriver.assertion.collection.result.AllResultStrategy;
import uk.co.stfo.adriver.assertion.collection.result.AtLeastOneResultStrategy;
import uk.co.stfo.adriver.assertion.collection.result.NoneResultStrategy;
import uk.co.stfo.adriver.assertion.collection.result.ResultStrategy;
import uk.co.stfo.adriver.assertion.element.BaseElementAssertable;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Implementation of {@link CollectionAssertable} that performs all assertions
 * asynchronously by using a {@link Poller}.
 * 
 * @author sforbes
 * 
 */
public class AsyncCollectionAssertable implements CollectionAssertable {

    private final Poller poller;
    private final By by;
    private final Traversable parent;
    private final ElementFactory elementFactory;


    public AsyncCollectionAssertable(final By by, final Traversable parent, final Poller poller,
            final ElementFactory elementFactory) {
        this.by = by;
        this.parent = parent;
        this.poller = poller;
        this.elementFactory = elementFactory;
    }


    @Override
    public BaseElementAssertable allOf(final int expectedCollectionSize) {
        return createWithResultType(expectedCollectionSize, new AllResultStrategy());
    }


    @Override
    public BaseElementAssertable atLeastOneOf(final int expectedCollectionSize) {
        return createWithResultType(expectedCollectionSize, new AtLeastOneResultStrategy());
    }


    @Override
    public BaseElementAssertable noneOf(final int expectedCollectionSize) {
        return createWithResultType(expectedCollectionSize, new NoneResultStrategy());
    }


    public BaseElementAssertable createWithResultType(final int expectedCollectionSize,
            final ResultStrategy resultStrategy) {
        return new CollectionItemElementAssertable(expectedCollectionSize, by, parent, poller, elementFactory,
                resultStrategy);
    }
}
