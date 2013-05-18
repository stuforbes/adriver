package uk.co.stf.adriver.action;

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


    void select(String optionText);


    void perform(ElementAction action);

}
