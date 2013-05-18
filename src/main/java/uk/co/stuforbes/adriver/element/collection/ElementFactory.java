package uk.co.stuforbes.adriver.element.collection;

import uk.co.stuforbes.adriver.element.Element;
import uk.co.stuforbes.adriver.webdriver.Traversable;

public interface ElementFactory {

    Element createForPositionInList(int i, Traversable parent);
}
