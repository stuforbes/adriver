package uk.co.stfo.adriver.poll;

import org.hamcrest.StringDescription;

import uk.co.stfo.adriver.probe.Probe;

/**
 * Abstract implementation of {@link Poller} that simply throws an
 * {@link AssertionError} with an appropriate message if the probe is not
 * satisfied
 * 
 * @author sforbes
 * 
 */
public abstract class AbstractPoller implements Poller {

    @Override
    public void doProbe(final Probe probe) {
        final boolean isSatisfied = doPoll(probe);
        if (!isSatisfied) {
            throw new AssertionError(addFailureDescriptionFrom(probe));
        }
    }


    /**
     * Do a probe operation, returning the success status
     * 
     * @param probe
     *            The item to probe
     * @return true if the probe succeeds, otherwise false
     */
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
