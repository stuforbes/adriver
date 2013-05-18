package uk.co.stf.adriver.action.actions;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.action.ElementAction;
import uk.co.stf.adriver.action.actions.TypeTextAction;

public class TypeKeyActionTest extends AbstractActionTest {

    private static final String TEXT = "This is some text";


    @Override
    protected ElementAction createAction() {
        return new TypeTextAction(TEXT);
    }


    @Override
    protected void setExpectationsOn(final WebElement element, final JUnitRuleMockery context) {
        context.checking(new Expectations() {
            {
                oneOf(element).sendKeys(TEXT);
            }
        });
    }

}
