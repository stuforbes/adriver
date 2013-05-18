package uk.co.stuforbes.asyncdriver.element.collection;

import org.openqa.selenium.WebElement;

import uk.co.stuforbes.asyncdriver.element.ElementOperator;

import com.google.common.base.Predicate;

public interface ElementCollection {

    void each(int expectedCount, ElementOperator operator);


    void nth(int n, ElementOperator operator);


    void where(int expectedCount, Predicate<WebElement> predicate, ElementOperator elementOperator);
}
