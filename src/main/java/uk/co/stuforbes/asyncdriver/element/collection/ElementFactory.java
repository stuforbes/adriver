package uk.co.stuforbes.asyncdriver.element.collection;

import uk.co.stuforbes.asyncdriver.element.Element;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

public interface ElementFactory {

    Element createForPositionInList(int i, Traversable parent);
}
