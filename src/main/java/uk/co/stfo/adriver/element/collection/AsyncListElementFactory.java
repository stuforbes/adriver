package uk.co.stfo.adriver.element.collection;

import org.openqa.selenium.By;

import uk.co.stfo.adriver.action.ElementActionsFactory;
import uk.co.stfo.adriver.element.AsyncElement;
import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.poll.Poller;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Asynchronous implementation of {@link ElementFactory} that creates
 * {@link AsyncElement} items. Similar to the single {@link Element} items, this
 * one does not have to actually be present on the page at the time of
 * declaration.
 * 
 * @author sforbes
 * 
 */
public class AsyncListElementFactory implements ElementFactory {

    private final By by;
    private final Poller poller;
    private final ElementActionsFactory elementActionsFactory;


    public AsyncListElementFactory(final By by, final Poller poller, final ElementActionsFactory elementActionsFactory) {
        super();
        this.by = by;
        this.poller = poller;
        this.elementActionsFactory = elementActionsFactory;
    }


    @Override
    public Element createForPositionInList(final int i, final Traversable parent) {
        return new AsyncElement(by, poller, new CollectionItemTraversable(i, parent), elementActionsFactory);
    }

}
