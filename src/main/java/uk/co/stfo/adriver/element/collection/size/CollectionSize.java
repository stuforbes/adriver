package uk.co.stfo.adriver.element.collection.size;

import org.hamcrest.SelfDescribing;

import uk.co.stfo.adriver.element.collection.ElementCollection;

/**
 * Interface to define whether a satisfactory number of elements in an
 * {@link ElementCollection} have been located.
 * 
 * @author sforbes
 * 
 */
public interface CollectionSize extends SelfDescribing {

    /**
     * Is the current number of items that have been located satisfactory?
     * 
     * @param currentSize
     *            the number of items that have been found
     * @return true if satisfactory, otherwise falses
     */
    boolean isSatisfied(int currentSize);
}
