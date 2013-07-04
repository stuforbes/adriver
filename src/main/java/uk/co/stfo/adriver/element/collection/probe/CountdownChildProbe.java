package uk.co.stfo.adriver.element.collection.probe;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.element.collection.ElementOperator;
import uk.co.stfo.adriver.element.collection.size.CollectionSize;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Sub class of {@link EachChildProbe}, that simply invokes the
 * {@link ElementOperator} for the last element in the collection first, and
 * works backwards
 * 
 * @author sforbes
 * 
 */
public class CountdownChildProbe extends EachChildProbe {

    private static final Logger LOG = LoggerFactory.getLogger(CountdownChildProbe.class);


    public CountdownChildProbe(final By by, final Traversable parent, final CollectionSize collectionSize,
            final ElementOperator operator, final ElementFactory elementFactory) {
        super(by, parent, collectionSize, operator, elementFactory);
    }


    @Override
    protected void doElementOperation(final List<WebElement> elements) {
        for (int i = elements.size() - 1; i >= 0; i--) {
            if (isValidWebElement(i, elements.get(i))) {
                LOG.debug("WebElement {} at position {} is applicable. About to do element operation", elements.get(i),
                        i);
                final Element element = createElementForPosition(i);
                doWithElement(element);
            }
        }
    }
}
