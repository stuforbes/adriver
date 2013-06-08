package uk.co.stfo.adriver.assertion.collection;

import uk.co.stfo.adriver.assertion.element.BaseElementAssertable;
import uk.co.stfo.adriver.assertion.element.ElementAssertable;
import uk.co.stfo.adriver.element.collection.ElementCollection;
import uk.co.stfo.adriver.element.collection.size.CollectionSize;

/**
 * Assertion operations that can be performed on an {@link ElementCollection}.
 * 
 * @author sforbes
 * 
 */
public interface CollectionAssertable {

    /**
     * Provide an {@link BaseElementAssertable} implementation that ensures that
     * all elements of the collection meet this assertion
     * 
     * @param collectionSize
     *            The strategy for determining if a satisfactory number of
     *            elements are available
     * @return implementation of {@link ElementAssertable}
     */
    BaseElementAssertable allOf(CollectionSize collectionSize);


    /**
     * Provide an {@link BaseElementAssertable} implementation that ensures that
     * at least one (but potentially more) elements of the collection meet this
     * assertion
     * 
     * @param collectionSize
     *            The strategy for determining if a satisfactory number of
     *            elements are available
     * @return implementation of {@link ElementAssertable}
     */
    BaseElementAssertable atLeastOneOf(CollectionSize collectionSize);


    /**
     * Provide an {@link BaseElementAssertable} implementation that ensures that
     * no elements of the collection meet this assertion
     * 
     * @param collectionSize
     *            The strategy for determining if a satisfactory number of
     *            elements are available
     * @return implementation of {@link ElementAssertable}
     */
    BaseElementAssertable noneOf(CollectionSize collectionSize);
}
