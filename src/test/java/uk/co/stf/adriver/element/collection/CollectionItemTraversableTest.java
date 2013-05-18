package uk.co.stf.adriver.element.collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uk.co.stf.adriver.element.collection.CollectionItemTraversable;
import uk.co.stf.adriver.webdriver.Traversable;

public class CollectionItemTraversableTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Traversable parent;

    private Traversable traversable;


    @Before
    public void initialise() {
        this.parent = context.mock(Traversable.class);

        this.traversable = new CollectionItemTraversable(2, parent);
    }


    @Test
    public void traversingUsesCorrectPositionInCollection() {
        final WebElement element1 = context.mock(WebElement.class, "element1");
        final WebElement element2 = context.mock(WebElement.class, "element2");
        final WebElement element3 = context.mock(WebElement.class, "element3");
        final List<WebElement> elements = Arrays.asList(element1, element2, element3);

        final By by = By.id("an-id");
        context.checking(new Expectations() {
            {
                oneOf(parent).locateAllWith(by);
                will(returnValue(elements));
            }
        });

        assertThat(traversable.locateWith(by), is(element3));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void cannotTraverseUsingCollectionTraversable() {
        traversable.locateAllWith(By.id("an-id"));
    }
}
