package uk.co.stuforbes.asyncdriver.action.actions;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.openqa.selenium.WebElement;

import uk.co.stuforbes.asyncdriver.action.ElementAction;

public class SubmitActionTest extends AbstractActionTest {

    @Override
    protected ElementAction createAction() {
        return new SubmitAction();
    }


    @Override
    protected void setExpectationsOn(final WebElement element, final JUnitRuleMockery context) {
        context.checking(new Expectations() {
            {
                oneOf(element).submit();
            }
        });
    }

}
