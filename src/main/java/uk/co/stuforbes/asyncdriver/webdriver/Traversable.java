package uk.co.stuforbes.asyncdriver.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface Traversable {

    WebElement locateWith(By by);
}
