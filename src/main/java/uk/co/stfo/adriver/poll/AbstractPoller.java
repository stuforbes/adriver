package uk.co.stfo.adriver.poll;

import org.hamcrest.StringDescription;

import uk.co.stfo.adriver.probe.Probe;

public abstract class AbstractPoller implements Poller {

    @Override
    public void doProbe(final Probe probe) {
        final boolean isSatisfied = doPoll(probe);
        if (!isSatisfied) {
            throw new AssertionError(addFailureDescriptionFrom(probe));
        }
    }


    protected abstract boolean doPoll(Probe probe);


    private String addFailureDescriptionFrom(final Probe probe) {
        final StringDescription description = new StringDescription();

        description.appendText("\nWas expecting:\n    ");
        probe.describeTo(description);
        description.appendText("\nbut:\n    ");
        probe.describeFailureTo(description);

        return description.toString();
    }
}
