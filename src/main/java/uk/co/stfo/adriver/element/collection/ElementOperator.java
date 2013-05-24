package uk.co.stfo.adriver.element.collection;

import javax.lang.model.util.Elements;

import uk.co.stfo.adriver.element.Element;

/**
 * Allows implementations of this interface to operate on {@link Elements}
 * 
 * @author sforbes
 * 
 */
public interface ElementOperator {

    /**
     * Perform some operation on the {@link Element}
     * 
     * @param element
     *            To be operated on
     */
    void doWith(Element element);
}
