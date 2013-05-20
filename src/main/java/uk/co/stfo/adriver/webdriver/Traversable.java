package uk.co.stfo.adriver.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface Traversable {

    WebElement locateWith(By by);


    List<WebElement> locateAllWith(By by);
}
