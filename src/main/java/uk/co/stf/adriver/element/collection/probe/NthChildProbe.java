package uk.co.stf.adriver.element.collection.probe;

import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.element.ElementOperator;
import uk.co.stf.adriver.element.collection.ElementFactory;
import uk.co.stf.adriver.util.ByUtils;
import uk.co.stf.adriver.webdriver.Traversable;

public class NthChildProbe extends AbstractElementCollectionProbe {

    private final int n;


    public NthChildProbe(final By by, final Traversable parent, final int n, final ElementOperator operator,
            final ElementFactory elementFactory) {
        super(by, parent, operator, elementFactory);
        this.n = n;
    }


    @Override
    protected boolean isSatisfied(final int size) {
        return size > n;
    }


    @Override
    protected boolean isValidWebElement(final int position, final WebElement element) {
        return position == n;
    }


    @Override
    protected void descriptionOf(final By criteria, final Traversable parent, final int collectionSize,
            final Description description) {
        description.appendText("The ");
        description.appendText(ordinal(n));
        description.appendText(" child of parent ");
        description.appendText(parent.toString());
        description.appendText(" matching criteria ");
        description.appendText(ByUtils.asString(criteria));
    }


    @Override
    protected void failureDescriptionOf(final By criteria, final Traversable parent, final int collectionSize,
            final Description description) {
        description.appendText("Expected at least ");
        description.appendText(Integer.toString(n + 1));
        description.appendText(" elements under element ");
        description.appendText(parent.toString());
        description.appendText(" with criteria ");
        description.appendText(ByUtils.asString(criteria));
        description.appendText(", but instead found ");
        description.appendText(Integer.toString(collectionSize));
    }


    protected String ordinal(final int i) {
        final String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
        case 11:
        case 12:
        case 13:
            return i + "th";
        default:
            return i + sufixes[i % 10];

        }
    }
}
