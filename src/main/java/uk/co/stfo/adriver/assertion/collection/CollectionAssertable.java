package uk.co.stfo.adriver.assertion.collection;

import uk.co.stfo.adriver.assertion.element.BaseElementAssertable;
import uk.co.stfo.adriver.assertion.element.ElementAssertable;
import uk.co.stfo.adriver.element.collection.ElementCollection;

/**
 * Assertion operations that can be performed on an {@link ElementCollection}.
 * 
 * @author sforbes
 * 
 */
public interface CollectionAssertable {

    /**
     * Provide an {@link ElementAssertable} implementation that ensures that all
     * elements of the collection meet this assertion
     * 
     * @return implementation of {@link ElementAssertable}
     */
    BaseElementAssertable all();


    /**
     * Provide an {@link ElementAssertable} implementation that ensures that at
     * least one (but potentially more) elements of the collection meet this
     * assertion
     * 
     * @return implementation of {@link ElementAssertable}
     */
    BaseElementAssertable atLeastOne();


    /**
     * Provide an {@link ElementAssertable} implementation that ensures that no
     * elements of the collection meet this assertion
     * 
     * @return implementation of {@link ElementAssertable}
     */
    BaseElementAssertable none();
}
