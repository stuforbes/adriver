package uk.co.stf.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public abstract class MatcherProbe<T> implements Probe {

    private final Matcher<T> matcher;
    private final String descriptionContext;


    public MatcherProbe(final String descriptionContext, final Matcher<T> matcher) {
        this.descriptionContext = descriptionContext;
        this.matcher = matcher;
    }


    public void doProbe() {
        // No-op
    }


    public boolean isSatisfied() {
        return matcher.matches(content());
    }


    public void describeTo(final Description description) {
        description.appendText(descriptionContext);
        description.appendText(" that matches '");
        description.appendDescriptionOf(matcher);
        description.appendText("'");
    }


    public void describeFailureTo(final Description description) {
        description.appendText(descriptionContext);
        description.appendText(" does not match '");
        description.appendDescriptionOf(matcher);
        description.appendText("'");
    }


    protected abstract T content();
}
