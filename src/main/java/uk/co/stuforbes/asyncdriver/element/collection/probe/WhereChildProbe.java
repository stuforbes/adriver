package uk.co.stuforbes.asyncdriver.element.collection.probe;

import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stuforbes.asyncdriver.element.ElementOperator;
import uk.co.stuforbes.asyncdriver.element.collection.ElementFactory;
import uk.co.stuforbes.asyncdriver.util.ByUtils;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

import com.google.common.base.Predicate;

public class WhereChildProbe extends AbstractElementCollectionProbe {

    private final int expectedCount;
    private final Predicate<WebElement> predicate;


    public WhereChildProbe(final By by, final Traversable parent, final int expectedCount,
            final Predicate<WebElement> predicate, final ElementOperator operator, final ElementFactory elementFactory) {
        super(by, parent, operator, elementFactory);
        this.expectedCount = expectedCount;
        this.predicate = predicate;
    }


    @Override
    protected boolean isSatisfied(final int size) {
        return size == expectedCount;
    }


    @Override
    protected boolean isValidWebElement(final int position, final WebElement element) {
        return predicate.apply(element);
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