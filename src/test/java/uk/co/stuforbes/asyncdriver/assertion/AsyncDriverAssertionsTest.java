package uk.co.stuforbes.asyncdriver.assertion;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import uk.co.stuforbes.asyncdriver.poll.DoOnceOnlyPoller;

public class AsyncDriverAssertionsTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private DoOnceOnlyPoller poller;
    private WebDriver webDriver;

    private DriverAssertable assertions;


    @Before
    public void initialise() {

        this.poller = new DoOnceOnlyPoller();
        this.webDriver = context.mock(WebDriver.class);

        this.assertions = new AsyncDriverAssertable(poller, webDriver);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void assertPageSourceIsValidIfPageSourceMatches() {

        final String pageSource = "This is a page source";
        final Matcher<String> pageSourceMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                oneOf(webDriver).getPageSource();
                will(returnValue(pageSource));

                oneOf(pageSourceMatcher).matches(pageSource);
                will(returnValue(true));
            }
        });

        assertions.thatPageSource(pageSourceMatcher);
        assertThat(poller.isSatisfied(), is(true));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void assertPageSourceIsInvalidIfPageSourceDoesNotMatch() {

        final String pageSource = "This is a page source";
        final Matcher<String> pageSourceMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                oneOf(webDriver).getPageSource();
                will(returnValue(pageSource));

                oneOf(pageSourceMatcher).matches(pageSource);
                will(returnValue(false));
            }
        });

        assertions.thatPageSource(pageSourceMatcher);
        assertThat(poller.isSatisfied(), is(false));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void assertCurrentUrlIsValidIfCurrentUrlMatches() {

        final String currentUrl = "http://valid.com";
        final Matcher<String> currentUrlMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                oneOf(webDriver).getCurrentUrl();
                will(returnValue(currentUrl));

                oneOf(currentUrlMatcher).matches(currentUrl);
                will(returnValue(true));
            }
        });

        assertions.thatCurrentUrl(currentUrlMatcher);
        assertThat(poller.isSatisfied(), is(true));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void assertCurrentUrlIsInvalidIfCurrentUrlDoesNotMatch() {

        final String currentUrl = "http://invalid.com";
        final Matcher<String> currentUrlMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                oneOf(webDriver).getCurrentUrl();
                will(returnValue(currentUrl));

                oneOf(currentUrlMatcher).matches(currentUrl);
                will(returnValue(false));
            }
        });

        assertions.thatCurrentUrl(currentUrlMatcher);
        assertThat(poller.isSatisfied(), is(false));
    }
}
