package uk.co.stf.adriver.assertion;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import uk.co.stf.adriver.assertion.AsyncDriverAssertable;
import uk.co.stf.adriver.assertion.DriverAssertable;
import uk.co.stf.adriver.poll.Poller;
import uk.co.stf.adriver.poll.DoOnceOnlyPoller;

public class AsyncDriverAssertionsTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Poller poller;
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

                oneOf(pageSourceMatcher).describeTo(with(any(Description.class)));
                oneOf(pageSourceMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertions.thatPageSource(pageSourceMatcher);
            fail("Expected an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(ex.getMessage(), containsString("Was expecting:\n    Page Source that matches"));
            assertThat(ex.getMessage(), containsString("but:\n    Page Source does not match"));
        }
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

                oneOf(currentUrlMatcher).describeTo(with(any(Description.class)));
                oneOf(currentUrlMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertions.thatCurrentUrl(currentUrlMatcher);
            fail("Expected an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(ex.getMessage(), containsString("Was expecting:\n    Current URL that matches"));
            assertThat(ex.getMessage(), containsString("but:\n    Current URL does not match"));
        }
    }
}
