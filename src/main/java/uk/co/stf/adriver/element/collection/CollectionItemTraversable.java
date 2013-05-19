package uk.co.stf.adriver.element.collection;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.webdriver.Traversable;

public class CollectionItemTraversable implements Traversable {

    private final int n;
    private final Traversable collectionContainer;


    public CollectionItemTraversable(final int n, final Traversable collectionContainer) {
        this.n = n;
        this.collectionContainer = collectionContainer;
    }


    @Override
    public WebElement locateWith(final By by) {
        return collectionContainer.locateAllWith(by).get(n);
    }


    @Override
    public List<WebElement> locateAllWith(final By by) {
        throw new UnsupportedOperationException("Not supported");
    }

}
