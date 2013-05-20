package uk.co.stfo.adriver.element.collection.probe;

import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stfo.adriver.element.ElementOperator;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.util.ByUtils;
import uk.co.stfo.adriver.webdriver.Traversable;

public class EachChildProbe extends AbstractElementCollectionProbe {

    private static final Logger LOG = LoggerFactory.getLogger(EachChildProbe.class);

    private final int expectedCount;


    public EachChildProbe(final By by, final Traversable parent, final int expectedCount,
            final ElementOperator operator, final ElementFactory elementFactory) {
        super(by, parent, operator, elementFactory);
        this.expectedCount = expectedCount;
    }


    @Override
    protected boolean isSatisfied(final int size) {
        LOG.debug("Checking if size {} == expectedCount {}", size, expectedCount);
        return size == expectedCount;
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
        description.appendText("Expected ");
        description.appendText(Integer.toString(expectedCount));
        description.appendText(" elements under element ");
        description.appendText(parent.toString());
        description.appendText(" with criteria ");
        description.appendText(ByUtils.asString(criteria));
        description.appendText(", but instead found ");
        description.appendText(Integer.toString(collectionSize));
    }
}
