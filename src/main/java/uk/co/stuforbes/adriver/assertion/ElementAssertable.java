package uk.co.stuforbes.adriver.assertion;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

public interface ElementAssertable {

    void doesExist();


    void doesNotExist();


    void hasAttribute(String attributeName, Matcher<String> valueMatcher);


    void hasText(Matcher<String> textMatcher);


    void matches(Matcher<WebElement> elementMatcher);
}
