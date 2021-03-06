package uk.co.stfo.adriver.assertion;

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

import uk.co.stfo.adriver.assertion.driver.AsyncDriverAssertable;
import uk.co.stfo.adriver.assertion.driver.DriverAssertable;
import uk.co.stfo.adriver.poll.DoOnceOnlyPoller;
import uk.co.stfo.adriver.poll.Poller;

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

        assertions.pageSource(pageSourceMatcher);
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
            assertions.pageSource(pageSourceMatcher);
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

        assertions.currentUrl(currentUrlMatcher);
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
            assertions.currentUrl(currentUrlMatcher);
            fail("Expected an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(ex.getMessage(), containsString("Was expecting:\n    Current URL that matches"));
            assertThat(ex.getMessage(), containsString("but:\n    Current URL does not match"));
        }
    }


    @SuppressWarnings("unchecked")
    @Test
    public void assertTitleIsValidIfTitleMatches() {

        final String title = "Page title";
        final Matcher<String> titleMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                oneOf(webDriver).getTitle();
                will(returnValue(title));

                oneOf(titleMatcher).matches(title);
                will(returnValue(true));
            }
        });

        assertions.title(titleMatcher);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void assertTitleIsInvalidIfTitleDoesNotMatch() {

        final String title = "Invalid Title";
        final Matcher<String> titleMatcher = context.mock(Matcher.class);

        context.checking(new Expectations() {
            {
                oneOf(webDriver).getTitle();
                will(returnValue(title));

                oneOf(titleMatcher).matches(title);
                will(returnValue(false));

                oneOf(titleMatcher).describeTo(with(any(Description.class)));
                oneOf(titleMatcher).describeTo(with(any(Description.class)));
            }
        });

        try {
            assertions.title(titleMatcher);
            fail("Expected an " + AssertionError.class.getName());
        } catch (final AssertionError ex) {
            assertThat(ex.getMessage(), containsString("Was expecting:\n    Title that matches"));
            assertThat(ex.getMessage(), containsString("but:\n    Title does not match"));
        }
    }
}
