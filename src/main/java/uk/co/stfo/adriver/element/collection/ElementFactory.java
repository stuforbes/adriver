package uk.co.stfo.adriver.element.collection;

import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.webdriver.Traversable;

public interface ElementFactory {

    Element createForPositionInList(int i, Traversable parent);
}
