package uk.co.stuforbes.adriver.action;

import uk.co.stuforbes.adriver.element.Element;

public interface ElementActionsFactory {

    ElementActions createActionsFor(Element element);
}
