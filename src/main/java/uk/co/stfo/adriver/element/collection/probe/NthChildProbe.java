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
 * on only the nth item in a collection
 * 
 * @author sforbes
 * 
 */
public class NthChildProbe extends AbstractElementCollectionProbe {

    private static final Logger LOG = LoggerFactory.getLogger(NthChildProbe.class);

    private final int n;


    public NthChildProbe(final By by, final Traversable parent, final int n, final ElementOperator operator,
            final ElementFactory elementFactory) {
        super(by, parent, operator, elementFactory);
        this.n = n;
    }


    @Override
    protected boolean isSatisfied(final int size) {
        LOG.debug("Checking if size {} is greater than n {}", size, n);
        return size > n;
    }


    @Override
    protected boolean isValidWebElement(final int position, final WebElement element) {
        LOG.debug("Checking if position {} == n {}", position, n);
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
