package uk.co.stfo.adriver.action;

import uk.co.stfo.adriver.element.Element;

/**
 * Factory interface to create new {@link ElementActions} implementations,
 * relative to an {@link Element}
 * 
 * @author sforbes
 * 
 */
public interface ElementActionsFactory {

    ElementActions createActionsFor(Element element);
}
