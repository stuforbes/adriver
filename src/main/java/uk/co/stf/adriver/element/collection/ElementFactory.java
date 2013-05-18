package uk.co.stf.adriver.element.collection;

import uk.co.stf.adriver.element.Element;
import uk.co.stf.adriver.webdriver.Traversable;

public interface ElementFactory {

    Element createForPositionInList(int i, Traversable parent);
}
