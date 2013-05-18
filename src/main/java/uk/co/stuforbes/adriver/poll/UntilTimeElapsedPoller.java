package uk.co.stuforbes.adriver.poll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.stuforbes.adriver.probe.Probe;

public class UntilTimeElapsedPoller extends AbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(UntilTimeElapsedPoller.class);

    private long startTime;
    private final long timeout;
    private final long pollFrequency;


    public UntilTimeElapsedPoller(final long timeout, final long pollFrequency) {
        this.timeout = timeout;
        this.pollFrequency = pollFrequency;
    }


    @Override
    public void doProbe(final Probe probe) {
        LOG.debug("Starting poll with the probe " + probe.getClass().getName());
        startTheClock();

        super.doProbe(probe);

        stopTheClock();
    }


    @Override
    protected boolean doPoll(final Probe probe) {
        do {
            probe.doProbe();

            sleep();
        } while (!satisfied(probe) && !timedout());

        return satisfied(probe);
    }


    private boolean satisfied(final Probe probe) {
        final boolean result = probe.isSatisfied();
        if (result) {
            LOG.debug("Probe " + probe.getClass().getName() + " is satisfied");
        }
        return result;
    }


    private boolean timedout() {
        final long time = now() - startTime;
        final boolean timedout = time > timeout;
        if (timedout) {
            LOG.debug("Probe has timed out, time is " + time);
        }
        return timedout;
    }


    private void startTheClock() {
        startTime = now();
        LOG.debug("Starting clock, time is " + (now() - startTime));
    }


    private long now() {
        return System.currentTimeMillis();
    }


    private void stopTheClock() {
        startTime = 0;
    }


    private void sleep() {
        try {
            Thread.sleep(pollFrequency);
        } catch (final InterruptedException ex) {
            LOG.warn("Timer was interrupted", ex);
        }
    }
}
