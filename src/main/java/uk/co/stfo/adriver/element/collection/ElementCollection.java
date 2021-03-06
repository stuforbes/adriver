package uk.co.stfo.adriver.element.collection;

import org.openqa.selenium.WebElement;

import uk.co.stfo.adriver.assertion.collection.CollectionAssertable;
import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.size.CollectionSize;

import com.google.common.base.Predicate;

/**
 * Interface to represent operations that can be performed on collections of
 * {@link Element}s.
 * 
 * @author sforbes
 * 
 */
public interface ElementCollection {

    /**
     * Perform the {@link ElementOperator} on each element of the collection,
     * once the expected count has been reached
     * 
     * @param collectionSize
     *            The strategy for determining if a satisfactory number of
     *            elements are available
     * @param operator
     *            The operation to be performed on each {@link Element}
     */
    void each(CollectionSize collectionSize, ElementOperator operator);


    /**
     * Perform the {@link ElementOperator} on each element of the collection,
     * starting from the last element, working down the the first, once the
     * expected count has been reached
     * 
     * @param collectionSize
     *            The strategy for determining if a satisfactory number of
     *            elements are available
     * @param operator
     *            The operation to be performed on each {@link Element}
     */
    void countdown(CollectionSize collectionSize, ElementOperator operator);


    /**
     * Perform the {@link ElementOperator} on the nth element of the collection,
     * once there are at least n items available
     * 
     * @param n
     *            The index of the required {@link Element} in the collection
     *            (This is 0-based)
     * @param operator
     *            The operation to be performed on each {@link Element}
     */
    void nth(int n, ElementOperator operator);


    /**
     * Perform the {@link ElementOperator} on each element of the collection
     * that conform to the predicate, once the expected count has been reached
     * 
     * @param collectionSize
     *            The strategy for determining if a satisfactory number of
     *            elements are available
     * @param predicate
     *            Whether to apply the {@link ElementOperator}, based on
     *            characteristics of the underlying {@link WebElement}
     * @param operator
     *            The operation to be performed on each {@link Element}
     */
    void where(CollectionSize collectionSize, Predicate<WebElement> predicate, ElementOperator elementOperator);


    /**
     * Make assertions on the collection.
     * 
     * @return An {@link CollectionAssertable} implementation relative to this
     *         collection.
     */
    CollectionAssertable assertThat();
}
