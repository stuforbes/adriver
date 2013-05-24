package uk.co.stfo.adriver.element.collection;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.webdriver.Traversable;

/**
 * Factory interface to create an {@link Element} for a specific item in a
 * collection.
 * 
 * @author sforbes
 * 
 */
public interface ElementFactory {

    /**
     * Create an {@link Element} representing a specific Html item in a
     * collection
     * 
     * @param i
     *            The position, starting at 0, of the item in the collection
     * @param parent
     *            The position in the page that this collection is relative to
     * @return An {@link Element} implementation that represents the ith Html
     *         item in the collection
     */
    Element createForPositionInList(int i, Traversable parent);
}
