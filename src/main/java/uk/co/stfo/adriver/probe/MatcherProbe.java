package uk.co.stfo.adriver.probe;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Abstract implementation of {@link Probe} that performs no specific probe
 * action, and is satisfied when the subclasses content() method returns content
 * of type T that matches the defined {@link Matcher}.
 * 
 * @author sforbes
 * 
 * @param <T>
 *            The type of the content that this probe examines.
 */
public abstract class MatcherProbe<T> implements Probe {

    private final Matcher<T> matcher;
    private final String descriptionContext;


    public MatcherProbe(final String descriptionContext, final Matcher<T> matcher) {
        this.descriptionContext = descriptionContext;
        this.matcher = matcher;
    }


    @Override
    public void doProbe() {
        // No-op
    }


    @Override
    public boolean isSatisfied() {
        return matcher.matches(content());
    }


    @Override
    public void describeTo(final Description description) {
        description.appendText(descriptionContext);
        description.appendText(" that matches '");
        description.appendDescriptionOf(matcher);
        description.appendText("'");
    }


    @Override
    public void describeFailureTo(final Description description) {
        description.appendText(descriptionContext);
        description.appendText(" does not match '");
        description.appendDescriptionOf(matcher);
        description.appendText("'");
    }


    protected abstract T content();
}
