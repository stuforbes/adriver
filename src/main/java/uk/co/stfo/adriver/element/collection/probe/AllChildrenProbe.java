package uk.co.stfo.adriver.element.collection.probe;

import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.element.collection.ElementOperator;
import uk.co.stfo.adriver.util.ByUtils;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Sub class of {@link AbstractElementCollectionProbe} to perform an operation
 * on all items of a collection. Unlike the {@link EachChildProbe}, this probe
 * makes no guarantees that the elements are all fully loaded, only that there
 * is 1 or more {@link WebElement}s available
 * 
 * @author sforbes
 * 
 */
public class AllChildrenProbe extends AbstractElementCollectionProbe {

    private static final Logger LOG = LoggerFactory.getLogger(AllChildrenProbe.class);


    public AllChildrenProbe(final By by, final Traversable parent, final ElementOperator operator,
            final ElementFactory elementFactory) {
        super(by, parent, operator, elementFactory);
    }


    @Override
    protected boolean isSatisfied(final int size) {
        LOG.debug("Checking if size {} is greater than 0");
        return size > 0;
    }


    @Override
    protected boolean isValidWebElement(final int position, final WebElement element) {
        return true;
    }


    @Override
    protected void descriptionOf(final By criteria, final Traversable parent, final int collectionSize,
            final Description description) {
        description.appendText("Each child of parent ");
        description.appendText(parent.toString());
        description.appendText(" matching criteria ");
        description.appendText(ByUtils.asString(criteria));
    }


    @Override
    protected void failureDescriptionOf(final By criteria, final Traversable parent, final int collectionSize,
            final Description description) {
        description.appendText("Expected at least one element under element ");
        description.appendText(parent.toString());
        description.appendText(" with criteria ");
        description.appendText(ByUtils.asString(criteria));
        description.appendText(", but instead found ");
        description.appendText(Integer.toString(collectionSize));
    }
}
