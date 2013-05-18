package uk.co.stuforbes.adriver.element;

import org.openqa.selenium.By;

import uk.co.stuforbes.adriver.element.collection.ElementCollection;

public interface ElementContainer {

    Element child(By by);


    ElementCollection children(By by);
}
