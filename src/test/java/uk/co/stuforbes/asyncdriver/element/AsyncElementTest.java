package uk.co.stuforbes.asyncdriver.element;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stuforbes.asyncdriver.action.ElementActionsFactory;
import uk.co.stuforbes.asyncdriver.poll.Poller;
import uk.co.stuforbes.asyncdriver.webdriver.Traversable;

public class AsyncElementTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Poller poller;
    private Traversable parent;
    private ElementActionsFactory actionFactory;

    private By by;

    private AsyncElement element;


    @Before
    public void initialise() {

        this.poller = context.mock(Poller.class);
        this.parent = context.mock(Traversable.class);
        this.actionFactory = context.mock(ElementActionsFactory.class);

        this.by = By.id("an-id");

        this.element = new AsyncElement(by, poller, parent, actionFactory);
    }


    @Test
    public void findChildReturnsNewAsyncElement() {

        final Element child = element.child(By.id("an-id"));

        assertThat(child, is(instanceOf(AsyncElement.class)));
    }


    @Test
    public void findChildrenReturnsNewAsyncCollection() {

        final ElementCollection children = element.children(By.id("an-id"), 3);

        assertThat(children, is(instanceOf(AsyncElementCollection.class)));
    }


    @Test
    public void locateWithUsesParentsLocatedElementAsReferencePoint() {

        final WebElement parentElement = context.mock(WebElement.class, "parentElement");
        final WebElement thisElement = context.mock(WebElement.class, "thisElement");

        final By childBy = By.id("child-by");

        context.checking(new Expectations() {
            {
                oneOf(parent).locateWith(by);
                will(returnValue(parentElement));

                oneOf(parentElement).findElement(childBy);
                will(returnValue(thisElement));
            }
        });

        assertThat(element.locateWith(childBy), is(thisElement));
    }
}
