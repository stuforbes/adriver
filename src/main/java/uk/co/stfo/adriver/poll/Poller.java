package uk.co.stfo.adriver.poll;

import uk.co.stfo.adriver.probe.Probe;

public interface Poller {

    void doProbe(Probe probe);
}
