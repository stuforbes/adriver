package uk.co.stuforbes.asyncdriver.action;

import uk.co.stuforbes.asyncdriver.element.Element;

public interface ElementActionsFactory {

    ElementActions createActionsFor(Element element);
}
