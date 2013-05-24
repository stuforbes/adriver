package uk.co.stfo.adriver.element;

import org.openqa.selenium.By;

import uk.co.stfo.adriver.element.collection.ElementCollection;

/**
 * Interface to define an item that can contain {@link Element} items. At this
 * time, there are two interfaces that extend this:
 * <p/>
 * <ul>
 * <li>{@link Driver}</li>
 * <li>{@link Element}</li>
 * </ul>
 * 
 * @author sforbes
 * 
 */
public interface ElementContainer {

    /**
     * Find a reference to a single {@link Element}, relative to this item
     * 
     * @param by
     *            How to find this child
     * @return An {@link Element} representation of the child
     */
    Element child(By by);


    /**
     * Return an {@link ElementCollection} representation of Html items,
     * relative to this {@link ElementContainer}, matching the specified
     * {@link By} criteria
     * 
     * @param by
     *            How to find the children
     * @return An {@link ElementCollection} representation of the children.
     */
    ElementCollection children(By by);
}
