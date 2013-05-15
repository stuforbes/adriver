package uk.co.stuforbes.asyncdriver.action;

import org.openqa.selenium.Keys;

public interface ElementActions {

    void click();


    void doubleClick();


    void rightClick();


    void type(String text);


    void type(Keys key);


    void clear();


    void submit();


    void moveMouseOver();


    void perform(ElementAction action);

}
