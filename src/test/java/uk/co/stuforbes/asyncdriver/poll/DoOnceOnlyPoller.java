package uk.co.stuforbes.asyncdriver.poll;

import uk.co.stuforbes.asyncdriver.probe.Probe;

public class DoOnceOnlyPoller extends AbstractPoller {

    private final Boolean result = null;


    @Override
    public void doProbe(final Probe probe) {

        if (result != null) {
            throw new AssertionError("Probe has already been executed. Cannot executed more than once");
        }

        super.doProbe(probe);
    }


    @Override
    protected boolean doPoll(final Probe probe) {
        probe.doProbe();

        return probe.isSatisfied();
    }
}
