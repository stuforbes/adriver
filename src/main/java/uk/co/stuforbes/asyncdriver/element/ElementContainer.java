package uk.co.stuforbes.asyncdriver.element;

import org.openqa.selenium.By;

import uk.co.stuforbes.asyncdriver.element.collection.ElementCollection;

public interface ElementContainer {

    Element child(By by);


    ElementCollection children(By by);
}
