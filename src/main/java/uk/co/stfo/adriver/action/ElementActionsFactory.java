package uk.co.stfo.adriver.action;

import uk.co.stfo.adriver.element.Element;

public interface ElementActionsFactory {

    ElementActions createActionsFor(Element element);
}
