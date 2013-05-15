package uk.co.stuforbes.asyncdriver.poll;

import uk.co.stuforbes.asyncdriver.probe.Probe;

public class DoOnceOnlyPoller implements Poller {

    private Boolean result = null;


    public void doProbe(final Probe probe) {

        if (result != null) {
            throw new AssertionError("Probe has already been executed. Cannot executed more than once");
        }

        probe.doProbe();

        this.result = probe.isSatisfied();
    }


    public boolean isSatisfied() {
        if (result == null) {
            throw new AssertionError("Probe has not been executed yet");
        }
        return result.booleanValue();
    }
}
