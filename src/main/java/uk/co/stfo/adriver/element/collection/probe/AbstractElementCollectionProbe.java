package uk.co.stfo.adriver.element.collection.probe;

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

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.ElementFactory;
import uk.co.stfo.adriver.element.collection.ElementOperator;
import uk.co.stfo.adriver.probe.Probe;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Abstract implementation of {@link Probe} to handle collection operations.
 * Specifically, this probe will find all {@link WebElement}s in the page that
 * match the {@link By} criteria, and determine if this is satisfactory. If so,
 * each {@link WebElement} is checked by the subclass, and determined whether it
 * needs to be operated on. If so, the position is converted to an
 * {@link Element} using the {@link ElementFactory}, and the
 * {@link ElementOperator} invoked
 * 
 * @author sforbes
 * 
 */
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


    @Override
    public void describeFailureTo(final Description description) {
        failureDescriptionOf(by, parent, allElements.size(), description);
    }


    @Override
    public void describeTo(final Description description) {
        descriptionOf(by, parent, allElements.size(), description);
    }


    @Override
    public boolean isSatisfied() {
        return isSatisfied(allElements.size());
    }


    @Override
    public void doProbe() {
        try {
            LOG.debug("About to do probe");
            allElements = parent.locateAllWith(by);

            LOG.debug("Found {} elements", allElements.size());

            if (isSatisfied()) {
                LOG.debug("Probe is satisfied. About to do element operation");
                doElementOperation(allElements);
            } else {
                LOG.debug("Probe is not satisfied. About to do element operation");
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


    /**
     * Check each {@link WebElement}. If it is to be operated on, convert it to
     * an {@link Element} and invoke the {@link ElementOperator}
     * 
     * @param elements
     *            The {@link WebElement}s that exist on the page
     */
    protected void doElementOperation(final List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            if (isValidWebElement(i, elements.get(i))) {
                LOG.debug("WebElement {} at position {} is applicable. About to do element operation", elements.get(i),
                        i);
                final Element element = elementFactory.createForPositionInList(i, parent);
                operator.doWith(element);
            }
        }
    }


    protected void logException(final Exception ex) {
        LOG.debug(ex.getClass().getName() + "when searching for children of {} with criteria {}", parent, by);
    }


    /**
     * Has the collection size reached a satisfactory level
     * 
     * @param size
     *            The current size of the collection matching the criteria
     * @return True if the size is satisfactory, otherwise false
     */
    protected abstract boolean isSatisfied(int size);


    /**
     * Is it acceptable to operate on the {@link WebElement} in this position
     * 
     * @param position
     *            The position of this {@link WebElement} in the collection
     * @param element
     *            The {@link WebElement} representation from the page
     * @return True if this element should be operated on, false otherwise
     */
    protected abstract boolean isValidWebElement(int position, WebElement element);


    /**
     * Add a suitable description of this collection probe to the
     * {@link Description}
     * 
     * @param criteria
     *            The {@link By} used to locate the items of this collection
     * @param parent
     *            The item to which this collection is relative
     * @param collectionSize
     *            The number of items located
     * @param description
     *            Where the description is reported to
     */
    protected abstract void descriptionOf(By criteria, Traversable parent, int collectionSize, Description description);


    /**
     * Add a suitable description of the failure encountered by this collection
     * probe to the {@link Description}
     * 
     * @param criteria
     *            The {@link By} used to locate the items of this collection
     * @param parent
     *            The item to which this collection is relative
     * @param collectionSize
     *            The number of items located
     * @param description
     *            Where the description is reported to
     */
    protected abstract void failureDescriptionOf(By criteria, Traversable parent, int collectionSize,
            Description description);

}