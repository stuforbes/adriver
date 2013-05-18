package uk.co.stf.adriver.action;

import uk.co.stf.adriver.element.Element;

public interface ElementActionsFactory {

    ElementActions createActionsFor(Element element);
}
