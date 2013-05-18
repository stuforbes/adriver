package uk.co.stuforbes.adriver.action.actions;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stuforbes.adriver.action.ElementAction;

public class SelectOptionActionText extends AbstractActionTest {

    private static final String OPTION_TEXT = "An Option";


    @Override
    protected ElementAction createAction() {
        return new SelectOptionAction(OPTION_TEXT);
    }


    @Override
    protected void setExpectationsOn(final WebElement element, final JUnitRuleMockery context) {
        final WebElement optionElement = context.mock(WebElement.class, "optionElement");

        context.checking(new Expectations() {
            {
                oneOf(element).getTagName();
                will(returnValue("select"));

                oneOf(element).findElements(By.xpath(".//option[normalize-space(.) = \"" + OPTION_TEXT + "\"]"));
                will(returnValue(Arrays.asList(optionElement)));

                oneOf(optionElement).isSelected();
                will(returnValue(false));

                oneOf(optionElement).click();

                ignoring(element).getAttribute("multiple");
            }
        });
    }
}
