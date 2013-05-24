package uk.co.stfo.adriver.poll;

import uk.co.stfo.adriver.probe.Probe;

/**
 * Interface to repeatedly poll a {@link Probe} implementation
 * 
 * @author sforbes
 * 
 */
public interface Poller {

    /**
     * Perform a probe operation on the {@link Probe} implementation
     * 
     * @param probe
     *            The thing to be probed
     */
    void doProbe(Probe probe);
}
