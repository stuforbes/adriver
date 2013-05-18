package uk.co.stuforbes.asyncdriver.element.collection.probe;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.asyncdriver.element.Element;
import uk.co.stuforbes.asyncdriver.element.ElementOperator;
import uk.co.stuforbes.asyncdriver.element.collection.ElementFactory;
import uk.co.stuforbes.asyncdriver.probe.Probe;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

public abstract class AbstractElementCollectionProbe implements Probe {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractElementCollectionProbe.class);

    private List<WebElement> allElements;
    private final Traversable parent;
    private final By by;

    private final ElementOperator operator;

    private final ElementFactory elementFactory;


    public AbstractElementCollectionProbe(final By by, final Traversable parent, final ElementOperator operator,
            final ElementFactory elementFactory) {
        this.by = by;
        this.parent = parent;
        this.operator = operator;
        this.elementFactory = elementFactory;
        this.allElements = new ArrayList<WebElement>();
    }


    public void describeFailureTo(final Description description) {
        failureDescriptionOf(by, parent, allElements.size(), description);
    }


    public void describeTo(final Description description) {
        descriptionOf(by, parent, allElements.size(), description);
    }


    public boolean isSatisfied() {
        return isSatisfied(allElements.size());
    }


    public void doProbe() {
        try {
            allElements = parent.locateAllWith(by);
            if (isSatisfied()) {
                doElementOperation(allElements);
            }
        } catch (final NotFoundException ex) {
            // No-op, this may be expected behaviour
            logException(ex);
        } catch (final TimeoutException ex) {
            // No-op, this may be expected behaviour
            logException(ex);
        } catch (final StaleElementReferenceException ex) {
            // No-op, this may be expected behaviour
            logException(ex);
        } catch (final ElementNotVisibleException ex) {
            // No-op, this may be expected behaviour
            logException(ex);
        }
    }


    protected void doElementOperation(final List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            if (isValidWebElement(i, elements.get(i))) {
                final Element element = elementFactory.createForPositionInList(i, parent);
                operator.doWith(element);
            }
        }
    }


    protected void logException(final Exception ex) {
        LOG.debug(ex.getClass().getName() + "when searching for children of {} with criteria {}", parent, by);
    }


    protected abstract boolean isSatisfied(int size);


    protected abstract boolean isValidWebElement(int position, WebElement element);


    protected abstract void descriptionOf(By criteria, Traversable parent, int collectionSize, Description description);


    protected abstract void failureDescriptionOf(By criteria, Traversable parent, int collectionSize,
            Description description);

}