package uk.co.stfo.adriver.driver;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import uk.co.stfo.adriver.element.AsyncElement;
import uk.co.stfo.adriver.element.Element;
import uk.co.stfo.adriver.element.collection.AsyncElementCollection;
import uk.co.stfo.adriver.element.collection.ElementCollection;
import uk.co.stfo.adriver.poll.Poller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AsyncDriverTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private WebDriver webDriver;

    private AsyncDriver driver;


    @Before
    public void initialise() {
        final Poller poller = context.mock(Poller.class);
        this.webDriver = context.mock(WebDriver.class);

        this.driver = new AsyncDriver(poller, webDriver);
    }


    @Test
    public void findChildReturnsNewAsyncElement() {

        final Element child = driver.child(By.id("an-id"));

        assertThat(child, is(instanceOf(AsyncElement.class)));
    }


    @Test
    public void findChildrenReturnsNewAsyncCollection() {

        final ElementCollection children = driver.children(By.id("an-id"));

        assertThat(children, is(instanceOf(AsyncElementCollection.class)));
    }


    @Test
    public void navigateToDefersImmediatelyToWebDriver() {
        final String url = "a-url";

        context.checking(new Expectations() {
            {
                oneOf(webDriver).get(url);
            }
        });

        driver.navigateTo(url);
    }


    @Test
    public void closeDefersImmediatelyToWebDriver() {
        context.checking(new Expectations() {
            {
                oneOf(webDriver).close();
            }
        });

        driver.close();
    }


    @Test
    public void quitDefersImmediatelyToWebDriver() {
        context.checking(new Expectations() {
            {
                oneOf(webDriver).quit();
            }
        });

        driver.quit();
    }


    @Test
    public void canLocateWebElementsDirectlyFromWebDriver() {

        final By by = By.id("an-id");
        final WebElement element = context.mock(WebElement.class);

        context.checking(new Expectations() {
            {
                oneOf(webDriver).findElement(by);
                will(returnValue(element));
            }
        });

        assertThat(driver.locateWith(by), is(element));
    }


    @Test
    public void locateReturnsNullIfWebDriverCannotLocateIt() {

        final By by = By.id("an-id");

        context.checking(new Expectations() {
            {
                oneOf(webDriver).findElement(by);
                will(returnValue(null));
            }
        });

        assertThat(driver.locateWith(by), is(nullValue()));
    }

    @Test
    public void canPerformAnActionOnTheWebDriver(){
        final DriverAction action = context.mock(DriverAction.class);

        context.checking(new Expectations(){{
            oneOf(action).doActionOn(webDriver);
        }});

        driver.perform(action);
    }
}
