package uk.co.stuforbes.asyncdriver.element;

import org.openqa.selenium.By;

public interface ElementContainer {

    Element child(By by);


    ElementCollection children(By by, int expectedChildrenCount);
}
