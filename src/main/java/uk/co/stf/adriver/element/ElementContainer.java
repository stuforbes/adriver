package uk.co.stf.adriver.element;

import org.openqa.selenium.By;

import uk.co.stf.adriver.element.collection.ElementCollection;

public interface ElementContainer {

    Element child(By by);


    ElementCollection children(By by);
}
