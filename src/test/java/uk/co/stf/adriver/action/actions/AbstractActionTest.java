package uk.co.stf.adriver.action.actions;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.action.ElementAction;

public abstract class AbstractActionTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private ElementAction action;


    @Before
    public void initialise() {

        this.action = createAction();
    }


    @Test
    public void canPerformActionOnElement() {

        final WebElement element = context.mock(WebElement.class);

        setExpectationsOn(element, context);

        action.doActionOn(element);
    }


    protected abstract ElementAction createAction();


    protected abstract void setExpectationsOn(WebElement element, JUnitRuleMockery context);
}
