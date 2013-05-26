package uk.co.stfo.adriver.probe;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Test;

public class MatcherProbeTest {

    @Test
    public void isNotSatisfiedIfStringsDoNotMatch() {
        assertFalse(matcherFor("A non-matching string", is("A matching string")).isSatisfied());
    }


    @Test
    public void isSatisfiedIfStringsMatch() {
        assertTrue(matcherFor("A matching string", is("A matching string")).isSatisfied());
    }


    @Test
    public void probeShowsCorrectDescription() {
        final StringDescription description = new StringDescription();

        matcherFor("Some text", is("Some text")).describeTo(description);

        assertThat("Matcher that matches 'is \"Some text\"'", is(description.toString()));
    }


    @Test
    public void probeShowsCorrectFailureDescription() {
        final StringDescription description = new StringDescription();

        matcherFor("Some text", is("Some text")).describeFailureTo(description);

        assertThat("Matcher does not match 'is \"Some text\"'", is(description.toString()));
    }


    private Probe matcherFor(final String text, final Matcher<String> matcher) {
        return new MatcherProbe<String>("Matcher", matcher) {
            @Override
            protected String content() {
                return text;
            }
        };
    }
}
