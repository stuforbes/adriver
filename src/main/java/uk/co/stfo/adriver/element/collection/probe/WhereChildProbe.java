package uk.co.stfo.adriver.element.collection.probe;

import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.element.collection.ElementOperator;
import uk.co.stfo.adriver.element.collection.size.CollectionSize;
import uk.co.stfo.adriver.util.ByUtils;
import uk.co.stfo.adriver.webdriver.Traversable;

import com.google.common.base.Predicate;

/**
 * Sub class of {@link AbstractElementCollectionProbe} to perform an operation
 * on each item of a collection that conforms to the {@link Predicate}
 * 
 * @author sforbes
 * 
 */
public class WhereChildProbe extends AbstractElementCollectionProbe {

    private static final Logger LOG = LoggerFactory.getLogger(WhereChildProbe.class);

    private final Predicate<WebElement> predicate;

    private final CollectionSize collectionSize;


    public WhereChildProbe(final By by, final Traversable parent, final CollectionSize collectionSize,
            final Predicate<WebElement> predicate, final ElementOperator operator, final ElementFactory elementFactory) {
        super(by, parent, operator, elementFactory);
        this.collectionSize = collectionSize;
        this.predicate = predicate;
    }


    @Override
    protected boolean isSatisfied(final int size) {
        LOG.debug("Checking if size {} {}", size, collectionSize);
        return collectionSize.isSatisfied(size);
    }


    @Override
    protected boolean isValidWebElement(final int position, final WebElement element) {

        final boolean result = predicate.apply(element);
        if (result) {
            LOG.debug("WebElement {} at position {} matches predicate {}",
                    new Object[] { element, position, predicate });
        } else {
            LOG.debug("WebElement {} at position {} does not match predicate {}", new Object[] { element, position,
                    predicate });
        }
        return result;
    }


    @Override
    protected void descriptionOf(final By criteria, final Traversable parent, final int collectionSize,
            final Description description) {
        description.appendText("All children of parent ");
        description.appendText(parent.toString());
        description.appendText(" matching criteria ");
        description.appendText(ByUtils.asString(criteria));
        description.appendText(" and predicate ");
        description.appendText(predicate.toString());
    }


    @Override
    protected void failureDescriptionOf(final By criteria, final Traversable parent, final int size,
            final Description description) {
        description.appendText("Expected ");
        description.appendDescriptionOf(collectionSize);
        description.appendText(" elements under element ");
        description.appendText(parent.toString());
        description.appendText(" with criteria ");
        description.appendText(ByUtils.asString(criteria));
        description.appendText(", but instead found ");
        description.appendText(Integer.toString(size));
    }
}