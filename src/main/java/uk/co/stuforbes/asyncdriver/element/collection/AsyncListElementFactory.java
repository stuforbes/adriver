package uk.co.stuforbes.asyncdriver.element.collection;

import org.openqa.selenium.By;

import uk.co.stuforbes.asyncdriver.action.ElementActionsFactory;
import uk.co.stuforbes.asyncdriver.element.AsyncElement;
import uk.co.stuforbes.asyncdriver.element.Element;
import uk.co.stuforbes.asyncdriver.poll.Poller;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

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


    public Element createForPositionInList(final int i, final Traversable parent) {
        return new AsyncElement(by, poller, new CollectionItemTraversable(i, parent), elementActionsFactory);
    }

}
