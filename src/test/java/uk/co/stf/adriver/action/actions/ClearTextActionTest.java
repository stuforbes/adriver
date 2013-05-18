package uk.co.stf.adriver.action.actions;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.action.ElementAction;

public class ClearTextActionTest extends AbstractActionTest {

    @Override
    protected ElementAction createAction() {
        return new ClearTextAction();
    }


    @Override
    protected void setExpectationsOn(final WebElement element, final JUnitRuleMockery context) {
        context.checking(new Expectations() {
            {
                oneOf(element).clear();
            }
        });
    }

}
